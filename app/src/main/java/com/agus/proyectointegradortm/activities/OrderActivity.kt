package com.agus.proyectointegradortm.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.adapters.OrderAdapter
import com.agus.proyectointegradortm.databinding.ActivityOrderBinding
import com.agus.proyectointegradortm.fragments.OrderDetailFragment
import com.agus.proyectointegradortm.fragments.OrderListFragment
import com.agus.proyectointegradortm.fragments.ProductDetailFragment
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.utils.Communicator
import com.agus.proyectointegradortm.viewModels.OrderViewModel

class OrderActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityOrderBinding
    private var isDetailShown = false
    private lateinit var orderListFragment: OrderListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Historial de pedidos"

        orderListFragment = OrderListFragment()
        val bundle = Bundle()
        bundle.putString("title", "Historial de pedidos")
        orderListFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(binding.fragmentOrder.id, orderListFragment).commit()

    }

    override fun onBackPressed() {
        if (isDetailShown) {
            replaceFragment(orderListFragment)
            isDetailShown = false
        } else {
            super.onBackPressed()
        }
    }

    override fun passDataCom(order: Order) {
        val bundle = Bundle()
        bundle.putParcelable("selectedOrder", order)

        val orderDetailFragment = OrderDetailFragment()
        orderDetailFragment.arguments = bundle
        replaceFragment(orderDetailFragment)
        isDetailShown = true
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentOrder.id, fragment)
        transaction.commit()
    }

    override fun passDataCom(product: Product) {

    }

}