plugins {
    application
    java
}

application {
    mainClass.set("com.example.cli.CliApp")
}

dependencies {
    implementation(project(":core"))
}
