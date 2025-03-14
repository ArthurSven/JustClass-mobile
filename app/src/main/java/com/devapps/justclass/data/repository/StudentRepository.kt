package com.devapps.justclass.data.repository

import com.devapps.justclass.data.model.Student
import com.devapps.justclass.data.model.StudentResponse
import com.devapps.justclass.ui.state.Response
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.SupabaseEncodingException
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order

interface StudentRepository {

    suspend fun createStudent(student: Student) : Response

    suspend fun getStudentsByTeacher(userid: String) : List<StudentResponse>

    suspend fun getStudentByName(userid: String, student: String) : List<StudentResponse>

    suspend fun deleteStudent(studentid: String) : Response

}

class StudentRepositoryImpl(private val supabaseClient: SupabaseClient) : StudentRepository {

    override suspend fun createStudent(student: Student): Response {
        return try {
            supabaseClient.postgrest["student"].insert(listOf(student))
            Response.Success
        } catch (s : SupabaseEncodingException) {
            val message = s.localizedMessage ?: "Supabase Error: ${s.cause?.message}"
            Response.Error(Exception(message))
        } catch (e: Exception) {
            Response.Error(Exception("An unexpected error occurred: ${e.message}"))
        }
    }

    override suspend fun getStudentsByTeacher(userid: String): List<StudentResponse> {
        return try {
            val response = supabaseClient.postgrest["student"]
                .select {
                    filter {
                        eq("userid", userid)
                    }
                    order(column = "created_at", order = Order.DESCENDING)
                }
                .decodeList<StudentResponse>()
            response
        } catch (e: SupabaseEncodingException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }

    override suspend fun getStudentByName(userid: String, student: String): List<StudentResponse> {
        return try {
            val response = supabaseClient.postgrest["student"]
                .select {
                    filter {
                        eq("firstname", student)
                        eq("userid", userid)
                    }
                    order(column = "created_at", order = Order.DESCENDING)
                }
                .decodeList<StudentResponse>()
            response
        } catch (e: SupabaseEncodingException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }

    override suspend fun deleteStudent(studentid: String): Response {
        return try {
            supabaseClient.postgrest["student"]
                .delete {
                    filter {
                        eq("studentid", studentid)
                    }
                }
            Response.Success
        } catch (e: SupabaseEncodingException) {
            val errorMessage = e.message ?: "Supabase Error: ${e.cause?.message}" // Detailed error message
            Response.Error(Exception(errorMessage))
        } catch (e: Exception) {
            Response.Error(Exception("An unexpected error occurred: ${e.message}"))
        }
    }


}
