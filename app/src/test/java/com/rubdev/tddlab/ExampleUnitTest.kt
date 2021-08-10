package com.rubdev.tddlab

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ContactHelperTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val sharedPreferences = context.getSharedPreferences("com.rubdev.tddlab.PREFERENCES", Context.MODE_PRIVATE)
    private val contactHelper = ContactHelper(sharedPreferences)

    @Test
    fun `Quando chamar o metodo getListContacts() com 2 contatos , deve retornar uma lista de 2 contatos`() {
        //Prepara
        mockListaDoisContatos()
        // Valida
        val list = contactHelper.getListContacts()
        assertEquals(2, list.size)




    }

    @Test
    fun `Quando chamar o metodo getListContacts() sem contatos, deve retornar uma lista vazia`(){
        //Prepara
        mockListaDeContatosVazia()
        // Valida
        val list = contactHelper.getListContacts()
        assertEquals(0, list.size)
    }


    private fun mockListaDoisContatos(){
        contactHelper.setListContacts(
            arrayListOf(
                Contact(
                    "Tiago Silva",
                    "(11)99990-0000",
                    "img3.jpg"
                ),
                Contact(
                    "Nubia Santos",
                    "(84)9880-0560",
                    "img2.jpg"
                )
            )
        )
    }

    private fun mockListaDeContatosVazia(){
        contactHelper.setListContacts(arrayListOf())
    }
}