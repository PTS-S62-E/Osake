# Osake (sharing in Finnish)

This is the shared library for this organization, every application can use it to get the common functionality most applications need.

# Contributing
You can contribute buy forking this repo, or by submitting an issue here: https://github.com/PTS-S62-E/Osake/issues

# Kotlin
As you may have noticed, this library is written in Kotlin, a language that is 100% compatible with Java.
Learn more here http://kotlinlang.org/

# Artifactory Repository
To connect to the Artifactory repository, where for example the common library is hosted, you need to provide login credentials.
This can be done by adding a server entry to your local maven settings.

### Maven settings.xml location
Mac: ```~/.m2/settings.xml```

Windows: ```${user.home}/.m2/settings.xml```


### Adding authorization:
``` 
<servers>
        <server>
            <username>admin</username>
            <password>APBALjaZVr9YFcHPuBgypPagyHY</password>
            <id>central</id>
        </server>
        <server>
            <username>admin</username>
            <password>APBALjaZVr9YFcHPuBgypPagyHY</password>
            <id>snapshots</id>
        </server>
    </servers>
```
This should be added withing the ```settings```-tag in your settings.xml
