#!/bin/bash

./gradlew bootRun --args='--spring.profiles.active=demo --spring.datasource.url=jdbc:h2:mem:dev --spring.datasource.username=sa --spring.datasource.password='
