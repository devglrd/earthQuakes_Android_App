package ninja.irvyne.earthquakes.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Metadata {

    @SerializedName("generated")
    @Expose
    var generated: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("api")
    @Expose
    var api: String? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null

}