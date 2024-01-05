package com.unistop.network.api_response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class GetUniversityListResponse(
    @SerializedName("alpha_two_code")
    var alphaTwoCode: String?, // US
    @SerializedName("country")
    var country: String?, // United States
    @SerializedName("domains")
    var domains: List<String?>?,
    @SerializedName("name")
    var name: String?, // Marywood University
    @SerializedName("state-province")
    var stateProvince: String?, // Pennsylvania
    @SerializedName("web_pages")
    var webPages: List<String?>?
) : Parcelable
