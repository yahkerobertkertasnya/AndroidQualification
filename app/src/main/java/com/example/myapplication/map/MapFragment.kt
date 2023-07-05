package com.example.myapplication.map

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapFragment : Fragment() {

    private lateinit var mapView : MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.map_view, container, false)
        mapView = view.findViewById(R.id.map_view_comp)

        Configuration.getInstance().load(requireContext().applicationContext, PreferenceManager.getDefaultSharedPreferences(requireContext().applicationContext))
        Configuration.getInstance().userAgentValue = "com.example.myapplication"

        val geoPoint = GeoPoint(69, 309)
        mapView.controller.setCenter(geoPoint)
        mapView.controller.setZoom(18.0)
        val marker = Marker(mapView)
        marker.position = geoPoint
        marker.title = "POSITION"

        mapView.overlays.add(marker)

        return view
    }
}