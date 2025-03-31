package com.devapps.justclass.di

import com.devapps.justclass.Utils.Constants.API_KEY
import com.devapps.justclass.Utils.Constants.BASE_URL
import com.devapps.justclass.data.auth.GoogleAuthClient
import com.devapps.justclass.data.model.UserData
import com.devapps.justclass.data.repository.ClassRoomRepository
import com.devapps.justclass.data.repository.ClassRoomRepositoryImpl
import com.devapps.justclass.data.repository.PaymentRepository
import com.devapps.justclass.data.repository.PaymentRepositoryImpl
import com.devapps.justclass.data.repository.StudentRepository
import com.devapps.justclass.data.repository.StudentRepositoryImpl
import com.devapps.justclass.ui.viewmodels.ClassroomViewModel
import com.devapps.justclass.ui.viewmodels.GoogleAuthViewModel
import com.devapps.justclass.ui.viewmodels.StudentViewModel
import com.google.android.gms.auth.api.identity.SignInClient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SignInClient> {
        com.google.android.gms.auth.api.identity.Identity.getSignInClient(androidContext())
    }

    // Provide GoogleAuthClient with a dynamic Context
    single {
        GoogleAuthClient(
            context = androidContext(),
            oneTapClient = get() // Retrieve the SignInClient from the Koin container
        )
    }

    viewModel {
        GoogleAuthViewModel(
            googleAuthClient = get()
        )
    }

    viewModel { (userData: UserData) ->
        StudentViewModel(
            studentRepository = get(),
            userData = userData
        )
    }

    viewModel { (userData: UserData) ->
        ClassroomViewModel(
            classRoomRepository = get(),
            userData = userData
        )
    }

    single<SupabaseClient> {
        createSupabaseClient(
            BASE_URL,
            API_KEY
        ) {
            install(Postgrest)
        }
    }

    single {
        StudentRepositoryImpl(
            supabaseClient = get()
        )
    }

    single<StudentRepository> {
        StudentRepositoryImpl(
            supabaseClient = get()
        )
    }

    single {
        ClassRoomRepositoryImpl(
            supabaseClient = get()
        )
    }

    single<ClassRoomRepository> {
        ClassRoomRepositoryImpl(
            supabaseClient = get()
        )
    }

    single<PaymentRepositoryImpl> {
        PaymentRepositoryImpl(
            supabaseClient = get()
        )
    }

    single<PaymentRepository> {
        PaymentRepositoryImpl(
            supabaseClient = get()
        )
    }
}