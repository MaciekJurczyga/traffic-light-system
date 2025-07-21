plugins {
    application
    java
}

application {
    mainClass.set("org.example.Main")
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.4")
    implementation(project(":core"))
}
