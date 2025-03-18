package com.devapps.justclass.data.repository

import com.devapps.justclass.data.model.Classroom
import com.devapps.justclass.data.model.ClassroomResponse
import com.devapps.justclass.data.model.StudentResponse
import com.devapps.justclass.ui.state.Response
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.SupabaseEncodingException
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order

interface ClassRoomRepository {

    suspend fun createClassRoom(classroom: Classroom) : Response

    suspend fun getClassRoomByTeacher(userid: String) : List<ClassroomResponse>

    suspend fun getClassRoomsByLevel(level: String, userid: String) : List<ClassroomResponse>

    suspend fun getClassRoomByName(classname: String) : List<ClassroomResponse>

    suspend fun deleteClassRoom(classroomid: String) : Response

}

class ClassRoomRepositoryImpl(private val supabaseClient: SupabaseClient) : ClassRoomRepository {
    override suspend fun createClassRoom(classroom: Classroom): Response {
       return try {
           supabaseClient.postgrest["classroom"].insert(listOf(classroom))
           Response.Success
       } catch (s : SupabaseEncodingException) {
           val errorMessage = "Couldn't create class " + s.message
            Response.Error(Exception(errorMessage))
       }
    }

    override suspend fun getClassRoomByTeacher(userid: String): List<ClassroomResponse> {
        return try {
            val response = supabaseClient.postgrest["classroom"]
                .select {
                    filter {
                        eq("userid", userid)
                    }
                    order(column = "created_at", order = Order.DESCENDING)
                }
                .decodeList<ClassroomResponse>()
            response
        } catch (e: SupabaseEncodingException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }

    override suspend fun getClassRoomsByLevel(level: String, userid: String): List<ClassroomResponse> {
        return try {
            val response = supabaseClient.postgrest["classroom"]
                .select {
                    filter {
                        eq("level", level)
                        eq("userid", userid)
                    }
                    order(column = "created_at", order = Order.DESCENDING)
                }
                .decodeList<ClassroomResponse>()
            response
        } catch (e: SupabaseEncodingException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }

    override suspend fun getClassRoomByName(classname: String): List<ClassroomResponse> {
        return try {
            val response = supabaseClient.postgrest["classroom"]
                .select {
                    filter {
                        eq("", classname)
                    }
                    order(column = "created_at", order = Order.DESCENDING)
                }
                .decodeList<ClassroomResponse>()
            response
        } catch (e: SupabaseEncodingException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }

    override suspend fun deleteClassRoom(classroomid: String): Response {
        return try {
            supabaseClient.postgrest["classroom"]
                .delete {
                    filter {
                        eq("classroomid", classroomid)
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