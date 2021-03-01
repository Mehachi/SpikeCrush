package fr.Shams.NatureCollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.Shams.NatureCollection.fragments.AddPlantFragment
import fr.Shams.NatureCollection.fragments.CollectionFragment
import fr.Shams.NatureCollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        //importer le bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this),R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this),R.string.collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_plant_page ->{
                    loadFragment(AddPlantFragment(this),R.string.add_plant_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }

        }






    }
    private fun loadFragment(fragment: Fragment, string:Int){
        // charger notre repository
        val repo = PlantRepository()

        //actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        //mettre à jour la liste des plantes
        repo.updateData{
            // injecter le fragment dans notre boîte (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

    }


}

