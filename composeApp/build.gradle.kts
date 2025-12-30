import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    alias(libs.plugins.buildkonfig)

}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            //Ktor
            implementation(libs.ktor.client.okhttp)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //Material 3
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            // This library contains the NavigationSuiteScaffold for CMP 2025
            implementation(libs.material3.adaptive.navigation.suite)

            // UI components for all platforms (Android, iOS, Desktop, Web)
            implementation(libs.navigation3.ui)

            // Adaptive UI integration for all platforms
            implementation(libs.adaptive.navigation3)

            // Scoped ViewModels for all platforms
            implementation(libs.lifecycle.viewmodel.navigation3)

            //Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            //Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.serialization.core)


            //Viewmodel
            implementation(libs.androidx.lifecycle.viewmodelCompose)

            //Build config

        }
        iosMain.dependencies {
            //Ktor
            implementation(libs.ktor.client.darwin)

        }

        jsMain.dependencies {
            //Ktor
            implementation(libs.ktor.client.js)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

            //Ktor
            implementation(libs.ktor.client.java)

        }
    }
}

android {
    namespace = "org.subham.newsapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.subham.newsapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.subham.newsapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.subham.newsapp"
            packageVersion = "1.0.0"
        }
    }
}
buildkonfig {
    packageName = "org.subham.newsapp"

    val localProperties =
        Properties().apply {
            val propsFile = rootProject.file("local.properties")
            if (propsFile.exists()) {
                load(propsFile.inputStream())
            }
        }

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "NEWS_API_KEY",
            localProperties["NEWS_API_KEY"]?.toString() ?: "",
        )
    }
}