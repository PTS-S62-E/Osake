package com.pts62.common.finland.dto;

import com.pts62.common.facade.verplaatsing.TranslocationFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JourneyDto implements Serializable {

    private List<TranslocationFacade> translocations;

    public JourneyDto(List<TranslocationFacade> journeys) {
        this.translocations = journeys;
    }

    public JourneyDto() {
        this.translocations = new ArrayList<>();
    }

    public List<TranslocationFacade> getTranslocations() {
        return translocations;
    }

    public void setTranslocations(List<TranslocationFacade> translocations) {
        this.translocations = translocations;
    }
}
