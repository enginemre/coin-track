package template

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

enum class FlavorDimension {
    CONTENT_TYPE
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
@Suppress("EnumNaming")
enum class Flavor (val dimension : FlavorDimension, val applicationIdSuffix : String? = null) {
    beta(FlavorDimension.CONTENT_TYPE,".beta"),
    dev(FlavorDimension.CONTENT_TYPE,".dev"),
    prod(FlavorDimension.CONTENT_TYPE, )
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.CONTENT_TYPE.name
        productFlavors {
            Flavor.values().forEach{
                create(it.name) {
                    dimension = it.dimension.name
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            this.applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
