package com.devapps.justclass.data.repository

import com.devapps.justclass.data.model.Payment
import com.devapps.justclass.data.model.PaymentResponse
import com.devapps.justclass.ui.state.Response
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.SupabaseEncodingException
import io.github.jan.supabase.postgrest.postgrest

interface PaymentRepository {

    suspend fun createPayment(payment: Payment) : Response

    suspend fun getPaymentsByTeacher(teacherid: String) : List<PaymentResponse>

    suspend fun deletePayment(paymentid: String) : Response

}

class PaymentRepositoryImpl(private val supabaseClient: SupabaseClient) : PaymentRepository {
    override suspend fun createPayment(payment: Payment) : Response {
        try {
            supabaseClient.postgrest["payment"].insert(listOf(Payment))
            return Response.Success
        } catch (se: SupabaseEncodingException) {
            val errorMessage = "Couldn't create payment info: $se"
            return Response.Error(Exception(errorMessage))
        }
    }

    override suspend fun getPaymentsByTeacher(teacherid: String): List<PaymentResponse> {
        return try {
            val response = supabaseClient.postgrest["payment"]
                .select {
                    filter {
                        eq("userid", teacherid)
                    }
                }.decodeList<PaymentResponse>()
            response
        } catch (e: SupabaseEncodingException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        } catch (e: NoSuchElementException) {
            emptyList()
        }
    }

    override suspend fun deletePayment(paymentid: String): Response {
        return try {
            supabaseClient.postgrest["payment"]
                .delete {
                    filter {
                        eq("paymentid", paymentid)
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