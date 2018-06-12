package com.pts62.common.finland.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pts62.common.facade.verplaatsing.TranslocationFacade;
import com.pts62.common.finland.dto.AdministrationDto;
import com.pts62.common.finland.dto.CategoryDto;
import com.pts62.common.finland.dto.VehicleDto;
import com.pts62.common.finland.exceptions.CategoryException;
import com.pts62.common.finland.exceptions.CommunicationException;
import io.sentry.Sentry;

import javax.ejb.Singleton;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

@Singleton
public class RegistrationMovementService {

    private static String BASE_URL;

    /**
     * Use this to set the baseUrl for TEST environment.
     * @param baseUrl
     */
    public void setBaseUrl(String baseUrl){
        BASE_URL = baseUrl;
    }

    private Properties properties;

    private static RegistrationMovementService _instance;

    public static RegistrationMovementService getInstance() {
        if(_instance == null) {
            new RegistrationMovementService();
        }

        return _instance;
    }

    /**
     * Create a new RegistrationMoment instance. We want to load the "paths.properties" file here and throw an exception
     * is something goes wrong.
     *
     * The property file is used to get the endpoints that are needed for communication with the external api
     */
    private RegistrationMovementService() {
        InputStream input = null;
        properties = new Properties();
        try {
            _instance = this;
            input = getClass().getClassLoader().getResourceAsStream("paths.properties");
            properties.load(input);

            BASE_URL = properties.getProperty("BASE_URL");
        } catch (IOException e) {
            Sentry.capture(e);
            e.printStackTrace();
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Sentry.capture(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get the translocations for a vehicle by its ID and based on a start- and enddate.
     *
     * The date strings MUST be formatted as yyyy-MM-dd HH:mm
     * @param id VehicleID
     * @param startDate start date for your search
     * @param endDate end date for your search
     * @return Returns a list of ITranslocation for the specific period
     * @throws CommunicationException Thrown when there's an exception in communicating with the external API
     * @throws IOException Thrown when there is an exception in processing the data received from the external API
     */
    public AdministrationDto getTranslocationsForVehicleId(long id, String startDate, String endDate) throws CommunicationException, IOException {
        if(id < 1) { throw new CommunicationException("Please provide a vehicleId"); }

        String urlPart = properties.getProperty("TRANSLOCATION_FOR_VEHICLE_ID");

        urlPart = urlPart.replace(":id", String.valueOf(id));
        urlPart = urlPart.replace(":startdate", startDate);
        urlPart = urlPart.replace(":enddate", endDate);
        urlPart = urlPart.replace(" ", "%20");
        urlPart = urlPart.replace(":", "%3A");


        String url = BASE_URL + urlPart;

        String response = SendRequest.sendGet(url);

        if(response.isEmpty()) { return null; }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response, AdministrationDto.class);
    }

    public AdministrationDto getTranslocationsByLicensePlate(String licensePlate, String startDate, String endDate) throws CommunicationException, IOException {
        if(licensePlate == null || licensePlate.equals("")) { throw new CommunicationException("Please provide a licensePlate"); }

        String urlPart = properties.getProperty("TRANSLOCATION_BY_LICENSEPLATE");

        urlPart = urlPart.replace(":licensePlate", licensePlate);
        urlPart = urlPart.replace(":startdate", startDate);
        urlPart = urlPart.replace(":enddate", endDate);
        urlPart = urlPart.replace(" ", "%20");
        urlPart = urlPart.replace(":", "%3A");


        String url = BASE_URL + urlPart;

        String response = SendRequest.sendGet(url);

        if(response.isEmpty()) { return null; }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response, AdministrationDto.class);
    }

    /**
     * Create a tracking
     * @param licensePlate
     * @throws IOException
     * @throws CommunicationException
     */
    public void createTracking(String licensePlate) throws IOException, CommunicationException {
        String urlPart = properties.getProperty("CREATE_TRACKING");
        String url = BASE_URL + urlPart;
        ObjectMapper mapper = new ObjectMapper();
        SendRequest.sendPostPlainText(url, licensePlate);
    }

    /**
     * Delete a tracking
     * @param licensePlate
     * @throws IOException
     * @throws CommunicationException
     */
    public void deleteTracking(String licensePlate) throws IOException, CommunicationException {
        String urlPart = properties.getProperty("DELETE_TRACKING");
        urlPart = urlPart.replace(":licensePlate", licensePlate);
        String url = BASE_URL + urlPart;
        SendRequest.sendDelete(url);
    }

    /**
     * Get a single translocation from the movement registration api based on the ID of the translocations
     * @param id ID of the translocation
     * @return Returns a TranslocationDTO containing the Translocation that was fetched from the external api
     * @throws CommunicationException Thrown when there's an exception in processing the data from the external api
     * @throws IOException Thrown when there's an exception in communication with the external api
     */
    public TranslocationFacade getTranslocationById(long id) throws CommunicationException, IOException {
        if(id < 1) { throw new CommunicationException("Please provide a TranslocationId"); }

        String urlPart = properties.getProperty("TRANSLOCATION_BY_ID");
        urlPart = urlPart.replace(":id", String.valueOf(id));

        String url = BASE_URL + urlPart;

        String response = SendRequest.sendGet(url);

        if(response.isEmpty()) { return null; }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response, TranslocationFacade.class);
    }

    public CategoryDto getCategory(String name) throws CategoryException, IOException, CommunicationException {
        if (name.isEmpty()){
            throw new CategoryException("name cannot be empty");
        }

        String urlPart = properties.getProperty("GET_CATEGORY");
        urlPart = urlPart.replace(":name", name);
        String url = BASE_URL + urlPart;

        String response = SendRequest.sendGet(url);

        System.out.println(response);

        if (response.isEmpty()){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, CategoryDto.class);
    }

    public VehicleDto getVehicleById(long vehicleId) throws CommunicationException, IOException {
        if(vehicleId < 1) { throw new CommunicationException("Please provide a valid vehicleId"); }

        String urlPart = properties.getProperty("VEHICLE_BY_ID");
        urlPart = urlPart.replace(":id", String.valueOf(vehicleId));

        String url = BASE_URL + urlPart;

        String response = SendRequest.sendGet(url);

        Logger logger = Logger.getLogger(getClass().getName());
        logger.warning(url);

        if (response.isEmpty()){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response, VehicleDto.class);
    }

    public VehicleDto getVehicleByLicensePlate(String licensePlate) throws CommunicationException, IOException {
        if(licensePlate == null ||  licensePlate.equals("")) { throw new CommunicationException("Please provide a licenseplate"); }

        String urlPart = properties.getProperty("VEHICLE_BY_LICENSEPLATE");
        urlPart = urlPart.replace(":licensePlate", licensePlate);

        String url = BASE_URL + urlPart;

        String response = SendRequest.sendGet(url);

        Logger logger = Logger.getLogger(getClass().getName());
        logger.warning(url);

        if (response.isEmpty()){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response, VehicleDto.class);
    }

    public void createCategory(CategoryDto categoryDto) throws CategoryException, IOException, CommunicationException {
        if (categoryDto == null){
            throw new CategoryException("CategoryDto cannot be null");
        }

        if (categoryDto.getName().equals("")){
            throw new CategoryException("name cannot be empty");
        }

        String urlPart = properties.getProperty("CREATE_CATEGORY");
        String url = BASE_URL + urlPart;

        ObjectMapper mapper = new ObjectMapper();
        String categoryDtoAsJson = mapper.writeValueAsString(categoryDto);

        SendRequest.sendPost(url, categoryDtoAsJson);
    }
}
