package com.alexsapps.learncountriesandflagsgame.data.mappers

import com.alexsapps.learncountriesandflagsgame.data.entities.Countries
import com.alexsapps.learncountriesandflagsgame.data.pojos.ApiJsonResultItem

class ApiReturnedCountryToRoomCountryMapper {

    fun mapper(apiJsonResultItem: ApiJsonResultItem): Countries {
        return Countries(
            0,
            apiJsonResultItem.name.common,
            apiJsonResultItem.capital[0],
            apiJsonResultItem.region,
            apiJsonResultItem.flags.png
        )
    }
}