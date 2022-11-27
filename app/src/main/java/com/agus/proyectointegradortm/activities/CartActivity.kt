package com.agus.proyectointegradortm.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.adapters.CartAdapter
import com.agus.proyectointegradortm.databinding.ActivityCartBinding
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.viewModels.OrderViewModel
import com.agus.proyectointegradortm.viewModels.UserViewModel
import java.text.SimpleDateFormat
import java.util.Date


private const val CHANNEL_ID = "channel_id"
private const val notificationId = 1

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var userViewModel: UserViewModel
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = "Carrito de compras"

        // RecyclerView handling
        val recyclerView = binding.rvCartProducts
        // TODO: Este provider de las zapatillas esta puesto de modo temporal solamente para tener items
        adapter = CartAdapter(MyApplication.cart)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        // Ir a pagar boton
        var cartTotal: Double = 0.0
        MyApplication.cart.forEach { product ->
            cartTotal += product.price
        }
        binding.fabPay.text = "Pagar ahora: $" + cartTotal.toString()
        binding.fabPay.setOnClickListener {
            generateOrder(cartTotal)
            Toast.makeText(this, "Se ha completado el pedido!", Toast.LENGTH_SHORT).show()
        }

        createNotificationChannel()

    }

    private fun generateOrder(cartTotal: Double) {
        if (MyApplication.cart.size > 0) {
            val productsIDs = generateProductsString(MyApplication.cart)
            val idUser = MyApplication.preferences.getUserID()
            val currentDate = getCurrentDateAsString()
            val address = getUserAddress()
            var order = Order(
                0,
                idUser,
                productsIDs,
                currentDate,
                cartTotal,
                address
            )

            orderViewModel.addOrder(order)
            pushNotification()
            MyApplication.cart.clear()
            finish()
        } else {
            Toast.makeText(this, "El carrito esta vacio!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun pushNotification() {
        val builder = NotificationCompat.Builder(MyApplication.appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_shopping_cart)
            .setContentTitle("Orden confirmada")
            .setContentText("Haz generado un pedido exitosamente!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(MyApplication.appContext)) {
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        val name = "Order confirmation"
        val descriptionText = "Order confirmation"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
           getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun getUserAddress(): String {
        val user = userViewModel.getUserById(MyApplication.preferences.getUserID())

        return user.address
    }

    private fun getCurrentDateAsString(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        return currentDate
    }

    private fun generateProductsString(products: List<Product>): String {
        var string = ""
        products.forEach {
            string += "${it.id},"
        }
        string = string.substring(0, string.length - 1)
        return string
    }
}