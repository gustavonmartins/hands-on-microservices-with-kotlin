package com.microservices.chapter3

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object{
        val initialCustomers  = arrayOf(
                Customer(1, "Kotlin", Telephone("+44","7123456789")),
                Customer(2,"Spring"),
                Customer(3,"Microservices", Telephone("+44","7123456789"))
        )
    }
    val customers= ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int)=customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id]=customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        customers.remove(id)
        customers[id]=customer
    }

    override fun searchCustomers(nameFilter: String): List<Customer> =
        customers.filter { it.value.name.contains(nameFilter, true) }.map(Map.Entry<Int, Customer>::value).toList()

}