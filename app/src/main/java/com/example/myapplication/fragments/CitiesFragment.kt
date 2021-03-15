package com.example.myapplication.fragments

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.R
import com.example.myapplication.repository.network.responses.WeatherResponse
import com.example.myapplication.ui.recyclerview.CitiesAdapter
import com.example.myapplication.viewmodels.CitiesViewModel
import com.example.myapplication.viewmodels.MyViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.launch

class CitiesFragment : Fragment() {
    private val userLocation = MutableLiveData<Location>()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val PERMISSION_ID = 1
    private lateinit var context: MainActivity


    private val viewModel by viewModels<CitiesViewModel> {
        MyViewModelProvider()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context = activity as MainActivity
        rv_cities.layoutManager = LinearLayoutManager(activity)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        setLastLocation()

        setupObservers()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {
        userLocation.observe(viewLifecycleOwner, Observer {
            val lat = userLocation.value?.latitude
            val lon = userLocation.value?.longitude
            if (lat != null && lon != null)
                viewModel.setListOfNearCities(lat, lon, 10)
        })
        viewModel.cityList.observe(viewLifecycleOwner, Observer {
            rv_cities.adapter = CitiesAdapter(it as ArrayList<WeatherResponse>){ id -> goToDetails(id)}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_cities, container, false)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            context,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun checkPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }

        return false

    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isEnabled =
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        return isEnabled
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) { }
    }

    private fun setLastLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    if (it.isSuccessful && it.result != null) {
                        userLocation.value = it.result
                    } else {
                        setDefaultLocation()
                    }
                }
            } else {
                Toast.makeText(context, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
                setDefaultLocation()
            }
        } else {
            setDefaultLocation()
            Toast.
            makeText(activity, "Нет разрешения на получение местоположения", Toast.LENGTH_SHORT)
                .show()
            requestPermission()
        }
    }

    private fun setDefaultLocation() {
        val location = Location("myProvider")
        location.latitude = 55.751244
        location.longitude = 37.618423
        userLocation.value = location
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cities_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                lifecycleScope.launch {
                    val weather: WeatherResponse? = try {
                        query?.let {
                            viewModel.repository.getCityWeatherByName(it)
                        }
                    } catch (e: Exception) {
                            Snackbar.make(
                                fragment_cities_layout,
                                "Город не найден.",
                                Snackbar.LENGTH_LONG
                            ).show()
                        null
                    }
                    if (weather!= null) {
                        goToDetails(weather.id.toLong())
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun goToDetails(id: Long) {
        val fm = activity?.supportFragmentManager
        val transaction = fm?.beginTransaction()
        transaction?.replace(R.id.fragment_container, DetailsFragment(id))?.
        addToBackStack("cities")
        transaction?.commit()
    }

}