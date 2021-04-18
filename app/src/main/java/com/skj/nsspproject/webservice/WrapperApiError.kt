package com.skj.nsspproject.webservice;

data class WrapperApiError (val apiUrl : String = "",
                            val status : String = "OK",
                            val msg : String = "",
                            var validationErr : List<String> = emptyList<String>())