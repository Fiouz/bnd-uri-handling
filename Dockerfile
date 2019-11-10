FROM adoptopenjdk:11-jdk-hotspot

COPY . /bnd-gradle-project/
WORKDIR "/bnd-gradle-project"

ENV GRADLE_USER_HOME "/foo bar"

RUN chmod +x ./gradlew \
    # Initialize the Gradle Wrapper within the Docker image
    && ./gradlew --no-daemon --version

CMD [ \
    "./gradlew", \
    "--no-daemon", \
    "--stacktrace", \
    "-Dorg.gradle.internal.launcher.welcomeMessageEnabled=false", \
    "bndIndex" \
]
