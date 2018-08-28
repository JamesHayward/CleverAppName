package com.cleverappname.james.cleverappname


import com.google.gson.annotations.SerializedName

data class Geocode(@SerializedName("cc")
                   val cc: String = "",
                   @SerializedName("what")
                   val what: String = "",
                   @SerializedName("displayString")
                   val displayString: String = "",
                   @SerializedName("center")
                   val center: Center,
                   @SerializedName("where")
                   val where: String = "",
                   @SerializedName("longId")
                   val longId: String = "")


data class Meta(@SerializedName("code")
                val code: Int = 0,
                @SerializedName("requestId")
                val requestId: String = "")


data class Center(@SerializedName("lng")
                  val lng: Double = 0.0,
                  @SerializedName("lat")
                  val lat: Double = 0.0)


data class Venue(@SerializedName("name")
                 val name: String = "",
                 @SerializedName("location")
                 val location: Location,
                 @SerializedName("id")
                 val id: String = "",
                 @SerializedName("categories")
                 val categories: List<CategoriesItem>?,
                 @SerializedName("photos")
                 val photos: Photos)


data class Sw(@SerializedName("lng")
              val lng: Double = 0.0,
              @SerializedName("lat")
              val lat: Double = 0.0)


data class SuggestedFilters(@SerializedName("header")
                            val header: String = "",
                            @SerializedName("filters")
                            val filters: List<FiltersItem>?)


data class CategoriesItem(@SerializedName("pluralName")
                          val pluralName: String = "",
                          @SerializedName("name")
                          val name: String = "",
                          @SerializedName("icon")
                          val icon: Icon,
                          @SerializedName("id")
                          val id: String = "",
                          @SerializedName("shortName")
                          val shortName: String = "",
                          @SerializedName("primary")
                          val primary: Boolean = false)


data class FiltersItem(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("key")
                       val key: String = "")


data class ItemsItem(@SerializedName("venue")
                     val venue: Venue,
                     @SerializedName("reasons")
                     val reasons: Reasons,
                     @SerializedName("referralId")
                     val referralId: String = "")


data class Reasons(@SerializedName("count")
                   val count: Int = 0,
                   @SerializedName("items")
                   val items: List<ReasonItems>?)


data class ReasonItems(@SerializedName("summary")
                       val summary: String = "",
                       @SerializedName("type")
                       val type: String = "",
                       @SerializedName("reasonName")
                       val reasonName: String = "")


data class GroupsItem(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("type")
                      val type: String = "",
                      @SerializedName("items")
                      val items: List<ItemsItem>?)


data class Photos(@SerializedName("count")
                  val count: Int = 0)


data class Response(@SerializedName("suggestedFilters")
                    val suggestedFilters: SuggestedFilters,
                    @SerializedName("totalResults")
                    val totalResults: Int = 0,
                    @SerializedName("geocode")
                    val geocode: Geocode,
                    @SerializedName("headerFullLocation")
                    val headerFullLocation: String = "",
                    @SerializedName("headerLocationGranularity")
                    val headerLocationGranularity: String = "",
                    @SerializedName("groups")
                    val groups: List<GroupsItem>?,
                    @SerializedName("suggestedBounds")
                    val suggestedBounds: SuggestedBounds,
                    @SerializedName("headerLocation")
                    val headerLocation: String = "")


data class Foursquarevenues(@SerializedName("meta")
                            val meta: Meta,
                            @SerializedName("response")
                            val response: Response)


data class Ne(@SerializedName("lng")
              val lng: Double = 0.0,
              @SerializedName("lat")
              val lat: Double = 0.0)


data class LabeledLatLngsItem(@SerializedName("lng")
                              val lng: Double = 0.0,
                              @SerializedName("label")
                              val label: String = "",
                              @SerializedName("lat")
                              val lat: Double = 0.0)


data class Icon(@SerializedName("prefix")
                val prefix: String = "",
                @SerializedName("suffix")
                val suffix: String = "")


data class SuggestedBounds(@SerializedName("sw")
                           val sw: Sw,
                           @SerializedName("ne")
                           val ne: Ne)


data class Location(@SerializedName("cc")
                    val cc: String = "",
                    @SerializedName("country")
                    val country: String = "",
                    @SerializedName("address")
                    val address: String = "",
                    @SerializedName("labeledLatLngs")
                    val labeledLatLngs: List<LabeledLatLngsItem>?,
                    @SerializedName("lng")
                    val lng: Double = 0.0,
                    @SerializedName("formattedAddress")
                    val formattedAddress: List<String>?,
                    @SerializedName("city")
                    val city: String = "",
                    @SerializedName("postalCode")
                    val postalCode: String = "",
                    @SerializedName("state")
                    val state: String = "",
                    @SerializedName("crossStreet")
                    val crossStreet: String = "",
                    @SerializedName("lat")
                    val lat: Double = 0.0)


