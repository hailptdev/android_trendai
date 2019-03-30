package com.trend.ai.model.api.response.category

class CategoryRes {
    var child: ArrayList<Child>? = null
    var name: String? = null
    var id: String? = null
    var slug: String? = null
}

class Child  {
    var name: String? = null
    var id: String? = null
    var slug: String? = null
}