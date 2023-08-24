package com.example.learncountriesandflagsgame.data.mappers

import com.example.learncountriesandflagsgame.data.entities.Countries
import com.example.learncountriesandflagsgame.data.pojos.ApiJsonResultItem

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