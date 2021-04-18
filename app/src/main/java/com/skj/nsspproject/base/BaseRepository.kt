package com.skj.nsspproject.base

import com.skj.nsspproject.webservice.RestClient


open class BaseRepository(asyncViewController: AsyncViewController?) {

    val restClient: RestClient = RestClient()

    init {
        restClient.asyncViewController = asyncViewController
    }
}