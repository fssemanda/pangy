package ug.ledge.pangy.dataclasses

data class User(
    val id: Int?,
    val memberNo: String?,
    val middleName: String?,
    val uType: String?,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val nin: String?,
    val phone: String?,
    val dob: String?,
    val gender: String?,
    val profile_pic: String?,

    )

data class UserAccountData(
    val userId: User?,
    val acBalance: Float?,
    val interestEarned: Float?,
    val lastUpdateDate: String?
)

data class Property(
    val landLord: String?,
    val propertyName: String?,
    val city: String?,
    val town: String?,
    val latitude: String?,
    val longitude: String?,
    val propertyType: String?,
)


data class PropertyImages(
    val id: String?,
    val caption: String?,
    val description: String?,
    val property: String?,
    val propertyImage: String?,

    )

data class LeaseSubscriptions(
    val propertyId: String?,
    val landLord: String?,
    val tenant: String?,
    val cycle: String?,
    val unitPrice: String?,
    val unitType: String?,
    val startDate: String?,
    val endDate: String?,
    val is_active: String?,
    val lastUpdate: String?,
)

data class Transactions(
    val id: String?,
    val transactionType: String?,
    val amount: Double?,
    val receiver_firstName: String?,
    val receiver_lastName: String?,
    val initiator_uType: String?,
    val initiator: String?,
    val recipient: String?,
    val transactionTime: String?,
    val receiver_uType: String?,
    val transactionStatus: String?,
    val TransactionComments: String?,
    val initiatorComments: String?,
    val dateModified: String?,
    val lastModifiedBy: String?,

    )