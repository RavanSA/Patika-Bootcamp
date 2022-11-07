package android.project.assignmentweek5.data.remote.api.dto.users

data class UsersDTOItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)