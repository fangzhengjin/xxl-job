plugins {
    idea
    java
    alias(libs.plugins.spotless)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)
}

tasks {
    bootJar { enabled = false }
    jar { enabled = false }
}

/**
 * 总体项目配置(包括最外层build配置)
 */
allprojects {
    apply(plugin = "idea")
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    group = "com.xuxueli"
    version = "2.4.1"
}

spotless {
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

/**
 * 子模块配置(不包括最外层build配置)
 */
subprojects {
    apply(plugin = "idea")
    apply(plugin = "java")
    apply(plugin = rootProject.libs.plugins.spring.boot.get().pluginId)
    apply(plugin = rootProject.libs.plugins.spring.dependencyManagement.get().pluginId)
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)

    java {
        /**
         * 源码JDK版本
         */
        sourceCompatibility = JavaVersion.VERSION_21
        /**
         * 编译后字节码可运行环境的版本
         */
        targetCompatibility = JavaVersion.VERSION_21
    }
    spotless {
        if (plugins.hasPlugin("java")) {
            java {
//                googleJavaFormat()
                palantirJavaFormat("2.47.0").formatJavadoc(true)
                importOrder()
                removeUnusedImports()
                trimTrailingWhitespace()
                endWithNewline()
                formatAnnotations()
            }
        }
        yaml {
            target("src/**/*.yaml", "src/**/*.yml")
            // https://github.com/FasterXML/jackson-dataformats-text/blob/master/yaml/src/main/java/tools/jackson/dataformat/yaml/YAMLGenerator.java
            jackson()
                .yamlFeature("WRITE_DOC_START_MARKER", false)
                .yamlFeature("MINIMIZE_QUOTES", true)
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
    }

    tasks {
        bootJar {
            enabled = false
            archiveClassifier = ""
        }

        jar {
            enabled = true
            archiveClassifier = ""
            manifest {
                attributes["Implementation-Title"] = project.name
                attributes["Implementation-Version"] = project.version
                attributes["Implementation-Vendor-Id"] = project.group
            }
        }

        test {
            useJUnitPlatform()
        }
    }

    dependencies {
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    }
}
