package com.example.finalappliproject.Utilitis;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.finalappliproject.Interfaces.DataRetrievedListener;
import com.example.finalappliproject.Models.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    private static DataManager INSTANCE;
    private FirebaseFirestore db;
    FirebaseUser user;

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    private DataManager() {
        this.db = FirebaseFirestore.getInstance();
    }

    public static ArrayList<Trip> getAllTrips() {
        ArrayList<Trip> trips = new ArrayList<>();
        trips.addAll(getTripInIsrael());
        trips.addAll(getTripInUS());
        trips.addAll(getTripInEurope());
        trips.addAll(getTripInAsia());

        trips.add(new Trip("Angkor Wat Sunrise Tour, Cambodia", "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/73/d9/f5.jpg",
                "Easy", 240, "Witness the stunning sunrise over Angkor Wat, one of the most famous archaeological sites in the world. This early morning tour allows you to explore the intricate temple architecture and learn about its rich history without the crowds."
        ));

        trips.add(new Trip("Cinque Terre Hiking Tour, Italy", "https://57hours.com/wp-content/uploads/2023/01/cinque-terre-hiking.jpg",
                "Medium", 480, "Explore the picturesque villages of Cinque Terre, perched on the rugged cliffs of the Italian Riviera. This hiking tour offers stunning coastal views, colorful villages, and the chance to sample delicious local cuisine. The trails connect the five charming villages, each with its own unique charm."));

        trips.add(new Trip("Masada Sunrise Hike, Israel", "https://www.eggedtours.com/wp-content/uploads/2023/03/Masada5.jpg.webp",
                "Medium", 180, "Hike up the ancient fortress of Masada before dawn to witness a breathtaking sunrise over the Judean Desert. This historical site offers stunning views and insights into the dramatic history of King Herod’s palace and the Jewish-Roman wars."));

        trips.add(new Trip("Central Park Walking Tour, New York City, New York", "https://manhattanwalkingtour.com/wp-content/uploads/2020/05/Central-Park-Mall-Autumn-1.jpg",
                "Easy", 240, "Explore the iconic Central Park, a green oasis in the heart of Manhattan. This leisurely walking tour includes famous landmarks such as Bethesda Terrace, Bow Bridge, and Strawberry Fields. Perfect for those who want to enjoy a blend of nature and cityscapes without strenuous activity."));

        trips.add(new Trip("Angel's Landing Hike, Zion National Park, Utah", "https://wildlandtrekking.com/content/uploads/2023/03/Iconic-Angels-Landing-Ridge.jpg",
                "Hard", 480, "Known for its thrilling heights and breathtaking views, this hike is not for the faint of heart. The trail ascends 1,500 feet and includes narrow ridges with chains for support. The reward is a panoramic view of Zion Canyon, making it a bucket-list hike for adventure seekers."));

        return trips;
    }

    public static ArrayList<Trip> getTripInAsia() {
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(new Trip("Angkor Wat Sunrise Tour, Cambodia", "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/73/d9/f5.jpg",
                "Easy", 240, "This iconic trek takes you through the heart of the Himalayas, offering breathtaking views of some of the world's highest peaks, including Mount Everest. You'll pass through Sherpa villages, experience the local culture, and explore ancient monasteries along the way."));

        trips.add(new Trip("Tokyo City Highlights Tour, Japan", "https://storage.googleapis.com/tblv3_bucket_us/guides/63/63157/2023296005528641.jpg",
                "Easy", 480, "Discover the vibrant city of Tokyo on a guided tour that takes you to iconic landmarks such as the Tokyo Tower, Senso-ji Temple, and the bustling streets of Shibuya. Enjoy a mix of modern and traditional attractions in one of the world's most exciting cities."));

        trips.add(new Trip(" Halong Bay Day Cruise, Vietnam", "https://res.klook.com/image/upload/c_fill,w_750,h_560/q_80/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/qmgtdjekctlyucr8itqw.jpg",
                "Easy", 480, "Explore the stunning karst landscapes of Halong Bay on a day cruise. You'll sail through emerald waters, visit hidden caves, and enjoy a delicious seafood lunch on board, all while surrounded by breathtaking natural beauty."));

        trips.add(new Trip("Dubai Desert Safari, UAE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSC_FX7cVZkDftd0GHqpAXiVgdMV5cw4yi0Yg&s",
                "Medium", 90, "Experience the thrill of a desert safari in the Arabian Desert. This adventure includes dune bashing, camel riding, and a traditional Bedouin camp experience with dinner and cultural performances. It's a perfect mix of excitement and cultural immersion."));

        return trips;
    }

    //
    public static ArrayList<Trip> getTripInEurope() {
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(new Trip("Cinque Terre Hiking Tour, Italy", "https://57hours.com/wp-content/uploads/2023/01/cinque-terre-hiking.jpg",
                "Medium", 480, "Explore the picturesque villages of Cinque Terre, perched on the rugged cliffs of the Italian Riviera. This hiking tour offers stunning coastal views, colorful villages, and the chance to sample delicious local cuisine. The trails connect the five charming villages, each with its own unique charm."));

        trips.add(new Trip("Loch Ness and the Scottish Highlands, Scotland", "https://www.touristengland.com/wp-content/uploads/2022/01/loch-ness-scotland-adobe-805.jpg",
                "Easy", 600, "Embark on a scenic tour of the Scottish Highlands, including a visit to the legendary Loch Ness. Enjoy the dramatic landscapes, historic castles, and quaint villages. This trip includes a comfortable coach ride with stops for photo opportunities and exploring iconic locations."));

        trips.add(new Trip("Cliffs of Moher Adventure, Ireland", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGIlJoNqvH_H6_oHAZ5hGmcWNoJzSoUko-8A&s",
                "Medium", 540, "Discover the breathtaking Cliffs of Moher on this guided tour. The journey includes a coastal walk along the cliffs, offering spectacular ocean views and a chance to spot local wildlife. The trip combines nature, history, and the dramatic beauty of Ireland’s west coast."));

        return trips;
    }

    public static ArrayList<Trip> getTripInIsrael() {
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(new Trip("Masada Sunrise Hike", "https://www.eggedtours.com/wp-content/uploads/2023/03/Masada5.jpg.webp",
                "Medium", 180, "Hike up the ancient fortress of Masada before dawn to witness a breathtaking sunrise over the Judean Desert. This historical site offers stunning views and insights into the dramatic history of King Herod’s palace and the Jewish-Roman wars."));

        trips.add(new Trip("Tel Aviv Urban Bicycle Tour", "https://static.israel21c.org/www/uploads/2017/09/shutterstock_549899269-1000x657.jpg",
                "Easy", 180, "Explore the vibrant city of Tel Aviv on a guided bicycle tour. Pedal through the bustling streets, enjoy the coastal promenade, visit famous landmarks like the White City, and experience the city's unique blend of modernity and history."));

        trips.add(new Trip("Ein Gedi Nature Reserve Hike", "https://i0.wp.com/www.touristisrael.com/wp-content/uploads/2012/03/Ein-Gedi.jpg?resize=1024%2C683&ssl=1",
                "Medium", 120, "Discover the natural beauty of the Ein Gedi Nature Reserve. This hike takes you through lush oases, waterfalls, and the chance to spot wildlife like ibexes and rock hyraxes. Enjoy the serene environment and refreshing natural pools."));

        trips.add(new Trip("Rappelling in the Ramon Crater", "https://www.deepdesertisrael.com/wp-content/uploads/2019/10/20160609_172522-1.jpg",
                "Hard", 240, "Experience the thrill of rappelling down the cliffs of the Ramon Crater, one of Israel’s most stunning geological formations. This adventure offers an adrenaline rush along with spectacular views of the Negev Desert’s unique landscapes."));

        return trips;
    }

    public static ArrayList<Trip> getTripInUS() {
        ArrayList<Trip> trips = new ArrayList<>();

        trips.add(new Trip("Central Park Walking Tour, New York City, New York", "https://manhattanwalkingtour.com/wp-content/uploads/2020/05/Central-Park-Mall-Autumn-1.jpg",
                "Easy", 240, "Explore the iconic Central Park, a green oasis in the heart of Manhattan. This leisurely walking tour includes famous landmarks such as Bethesda Terrace, Bow Bridge, and Strawberry Fields. Perfect for those who want to enjoy a blend of nature and cityscapes without strenuous activity."));

        trips.add(new Trip("Angel's Landing Hike, Zion National Park, Utah", "https://wildlandtrekking.com/content/uploads/2023/03/Iconic-Angels-Landing-Ridge.jpg",
                "Hard", 480, "Known for its thrilling heights and breathtaking views, this hike is not for the faint of heart. The trail ascends 1,500 feet and includes narrow ridges with chains for support. The reward is a panoramic view of Zion Canyon, making it a bucket-list hike for adventure seekers."));

        trips.add(new Trip("Sonoma Valley Wine Tour, California", "https://media.tacdn.com/media/attractions-splice-spp-674x446/0e/ad/a1/2d.jpg",
                "Easy", 520, "Discover the beautiful vineyards of Sonoma Valley on a guided wine tour. Enjoy tastings at several renowned wineries, learn about the winemaking process, and savor a gourmet lunch. This relaxing trip combines scenic drives with delicious wine experiences."));

        trips.add(new Trip("Mount Monadnock Climb, New Hampshire", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg9NGocZVXCwnAG0kLCaXzu0UlPgA6tOdtGROCjp3D4UJ-27pmslGjfoXyl1l9mP_hWZlDErEbJg8zZtg8lwgOE11hTqYtj2Zyws7juuX4LBD8ms9-KnIrCr1BSNYXAgT5c7nI4KzNrd62MlTjGthUGl1S46ll8YQ-ypez-1QIwx68i-Y6WZgX1pvVcrQ/s1600/IMG_1925.jpg",
                "Medium", 480, "Tackle one of the most climbed mountains in the world with a day hike up Mount Monadnock. The trails offer a variety of terrains, and the summit provides stunning views of the surrounding countryside. It's a great choice for hikers looking for a manageable challenge."));


        return trips;
    }

    public static Map<String, Object> getAllTripsMap() {
        Map<String, Object> trips = new HashMap<>(getTripInIsraelMap()); // instead of putAll ( just for start )
        mergeMaps(trips, getTripInUSMap());
        mergeMaps(trips, getTripInEuropeMap());
        mergeMaps(trips, getTripInAsiaMap());

//        trips.add(new Recipe("Tomato Soup", "https://www.acouplecooks.com/wp-content/uploads/2021/09/Tomato-Soup-002.jpg",
//                "medium", 100, "\"1. Saute Aromatics – heat a non-reactive pot over medium heat. Melt in 4 Tbsp butter then sautee onions until softened and golden (10-12 min). Add minced garlic and saute another minute.\\n\\n\" +\n" +
//                "        \"2. Make the tomato soup base – stir in two 28 oz cans of crushed tomatoes with their juice, your chicken stock, chopped basil, sugar and black pepper. Bring to a boil then reduce heat, partially cover and simmer 10 minutes.\\n\\n\" +\n" +
//                "        \"3. Blend if desired – use an immersion blender in the pot or blend in batches using a blender (be careful not to overfill the blender with hot liquid) and return soup to the pot.\\n\\n\" +\n" +
//                "        \"4. Add cream and parmesan – stir in the heavy cream and shredded parmesan. Return to a simmer and season to taste if needed.\\n\\n\" +\n" +
//                "        \"5. Serve – ladle into warm bowls and garnish with more parmesan and basil.\";\n"
//        ));
//
//        trips.add(new Recipe("Pasta Carbonara", "https://www.allrecipes.com/thmb/Vg2cRidr2zcYhWGvPD8M18xM_WY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/11973-spaghetti-carbonara-ii-DDMFS-4x3-6edea51e421e4457ac0c3269f3be5157.jpg",
//                "hard", 130, "put pasta in the bucket, then wash some onions..."));
//
//        trips.add(new Recipe("Chocolate Cake", "https://www.mybakingaddiction.com/wp-content/uploads/2011/10/lr-0938-700x1050.jpg",
//                "easy", 50, "put flower in the bucket, then wash some chocolate..."));
//
//        trips.add(new Recipe("Meatball Spaghetti", "https://www.onceuponachef.com/images/2019/09/Spaghetti-and-Meatballs.jpg",
//                "medium", 70, "put pasta in the bucket, then wash some meat..."));
//
//        trips.add(new Recipe("Pizza Peperoni", "https://foodhub.scene7.com/is/image/woolworthsltdprod/2004-easy-pepperoni-pizza:Mobile-1300x1150",
//                "medium", 90, "put flower in the bucket, then put some peperoni..."));

        return trips;
    }

    public static Map<String, Object> getTripInAsiaMap() {

        Map<String, Object> trips = new HashMap<>();
        trips.put("0", new Trip("Angkor Wat Sunrise Tour, Cambodia", "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/73/d9/f5.jpg",
                "Easy", 240, "This iconic trek takes you through the heart of the Himalayas, offering breathtaking views of some of the world's highest peaks, including Mount Everest. You'll pass through Sherpa villages, experience the local culture, and explore ancient monasteries along the way."));

        trips.put("1", new Trip("Tokyo City Highlights Tour, Japan", "https://storage.googleapis.com/tblv3_bucket_us/guides/63/63157/2023296005528641.jpg",
                "Easy", 480, "Discover the vibrant city of Tokyo on a guided tour that takes you to iconic landmarks such as the Tokyo Tower, Senso-ji Temple, and the bustling streets of Shibuya. Enjoy a mix of modern and traditional attractions in one of the world's most exciting cities."));

        trips.put("2", new Trip("Halong Bay Day Cruise, Vietnam", "https://res.klook.com/image/upload/c_fill,w_750,h_560/q_80/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/qmgtdjekctlyucr8itqw.jpg",
                "Easy", 480, "Explore the stunning karst landscapes of Halong Bay on a day cruise. You'll sail through emerald waters, visit hidden caves, and enjoy a delicious seafood lunch on board, all while surrounded by breathtaking natural beauty."));

        trips.put("3", new Trip("Dubai Desert Safari, UAE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSC_FX7cVZkDftd0GHqpAXiVgdMV5cw4yi0Yg&s",
                "Medium", 90, "Experience the thrill of a desert safari in the Arabian Desert. This adventure includes dune bashing, camel riding, and a traditional Bedouin camp experience with dinner and cultural performances. It's a perfect mix of excitement and cultural immersion."));

        return trips;
    }

    //
    public static Map<String, Object> getTripInEuropeMap() {
        Map<String, Object> trips = new HashMap<>();
        trips.put("0", new Trip("Cinque Terre Hiking Tour, Italy", "https://57hours.com/wp-content/uploads/2023/01/cinque-terre-hiking.jpg",
                "Medium", 480, "Explore the picturesque villages of Cinque Terre, perched on the rugged cliffs of the Italian Riviera. This hiking tour offers stunning coastal views, colorful villages, and the chance to sample delicious local cuisine. The trails connect the five charming villages, each with its own unique charm."));

        trips.put("1", new Trip("Loch Ness and the Scottish Highlands, Scotland", "https://www.touristengland.com/wp-content/uploads/2022/01/loch-ness-scotland-adobe-805.jpg",
                "Easy", 600, "Embark on a scenic tour of the Scottish Highlands, including a visit to the legendary Loch Ness. Enjoy the dramatic landscapes, historic castles, and quaint villages. This trip includes a comfortable coach ride with stops for photo opportunities and exploring iconic locations."));

        trips.put("2", new Trip("Cliffs of Moher Adventure, Ireland", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGIlJoNqvH_H6_oHAZ5hGmcWNoJzSoUko-8A&s",
                "Medium", 540, "Discover the breathtaking Cliffs of Moher on this guided tour. The journey includes a coastal walk along the cliffs, offering spectacular ocean views and a chance to spot local wildlife. The trip combines nature, history, and the dramatic beauty of Ireland’s west coast."));

        return trips;
    }

    public static Map<String, Object> getTripInIsraelMap() {
        Map<String, Object> trips = new HashMap<>();
        trips.put("0", new Trip("Masada Sunrise Hike", "https://www.eggedtours.com/wp-content/uploads/2023/03/Masada5.jpg.webp",
                "Medium", 180, "Hike up the ancient fortress of Masada before dawn to witness a breathtaking sunrise over the Judean Desert. This historical site offers stunning views and insights into the dramatic history of King Herod’s palace and the Jewish-Roman wars."));

        trips.put("1", new Trip("Tel Aviv Urban Bicycle Tour", "https://static.israel21c.org/www/uploads/2017/09/shutterstock_549899269-1000x657.jpg",
                "Easy", 180, "Explore the vibrant city of Tel Aviv on a guided bicycle tour. Pedal through the bustling streets, enjoy the coastal promenade, visit famous landmarks like the White City, and experience the city's unique blend of modernity and history."));

        trips.put("2", new Trip("Ein Gedi Nature Reserve Hike", "https://i0.wp.com/www.touristisrael.com/wp-content/uploads/2012/03/Ein-Gedi.jpg?resize=1024%2C683&ssl=1",
                "Medium", 120, "Discover the natural beauty of the Ein Gedi Nature Reserve. This hike takes you through lush oases, waterfalls, and the chance to spot wildlife like ibexes and rock hyraxes. Enjoy the serene environment and refreshing natural pools."));

        trips.put("3", new Trip("Rappelling in the Ramon Crater", "https://www.deepdesertisrael.com/wp-content/uploads/2019/10/20160609_172522-1.jpg",
                "Hard", 240, "Experience the thrill of rappelling down the cliffs of the Ramon Crater, one of Israel’s most stunning geological formations. This adventure offers an adrenaline rush along with spectacular views of the Negev Desert’s unique landscapes."));

        return trips;
    }

    public static Map<String, Object> getTripInUSMap() {
        Map<String, Object> trips = new HashMap<>();

        trips.put("0", new Trip("Central Park Walking Tour, New York City, New York", "https://manhattanwalkingtour.com/wp-content/uploads/2020/05/Central-Park-Mall-Autumn-1.jpg",
                "Easy", 240, "Explore the iconic Central Park, a green oasis in the heart of Manhattan. This leisurely walking tour includes famous landmarks such as Bethesda Terrace, Bow Bridge, and Strawberry Fields. Perfect for those who want to enjoy a blend of nature and cityscapes without strenuous activity."));

        trips.put("1", new Trip("Angel's Landing Hike, Zion National Park, Utah", "https://wildlandtrekking.com/content/uploads/2023/03/Iconic-Angels-Landing-Ridge.jpg",
                "Hard", 480, "Known for its thrilling heights and breathtaking views, this hike is not for the faint of heart. The trail ascends 1,500 feet and includes narrow ridges with chains for support. The reward is a panoramic view of Zion Canyon, making it a bucket-list hike for adventure seekers."));

        trips.put("2", new Trip("Sonoma Valley Wine Tour, California", "https://media.tacdn.com/media/attractions-splice-spp-674x446/0e/ad/a1/2d.jpg",
                "Easy", 520, "Discover the beautiful vineyards of Sonoma Valley on a guided wine tour. Enjoy tastings at several renowned wineries, learn about the winemaking process, and savor a gourmet lunch. This relaxing trip combines scenic drives with delicious wine experiences."));

        trips.put("3", new Trip("Mount Monadnock Climb, New Hampshire", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg9NGocZVXCwnAG0kLCaXzu0UlPgA6tOdtGROCjp3D4UJ-27pmslGjfoXyl1l9mP_hWZlDErEbJg8zZtg8lwgOE11hTqYtj2Zyws7juuX4LBD8ms9-KnIrCr1BSNYXAgT5c7nI4KzNrd62MlTjGthUGl1S46ll8YQ-ypez-1QIwx68i-Y6WZgX1pvVcrQ/s1600/IMG_1925.jpg",
                "Medium", 480, "Tackle one of the most climbed mountains in the world with a day hike up Mount Monadnock. The trails offer a variety of terrains, and the summit provides stunning views of the surrounding countryside. It's a great choice for hikers looking for a manageable challenge."));


        return trips;
    }


    public void setTrip() {
        Map<String, Object> categoriesMap = new HashMap<>();
        categoriesMap.put("AllTrips", getAllTripsMap());
        categoriesMap.put("TripsInAsia", getTripInAsiaMap());
        categoriesMap.put("TripsInEurope", getTripInEuropeMap());
        categoriesMap.put("TripsInUS", getTripInUSMap());
        categoriesMap.put("TripsInIsrael", getTripInIsraelMap());

        // Assume we have a reference to the parent document
//        DocumentReference parentDocumentRef = db.collection(Constants.DBKeys.RECIPES).document("AllRecipes");
        String[] categories = {"AllTrips", "TripsInAsia", "TripsInEurope", "TripsInUS", "TripsInIsrael"};
        for (String category : categories) {

            // Create a reference to the collection
            CollectionReference collectionRef = db.collection(category);
            Map<String, Object> tempMap = (Map<String, Object>) categoriesMap.get(category);
            // Create a new document in the collection and set data
            collectionRef.document().set(tempMap.get("0"))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Data set to the subcollection successfully
                            Log.d("TAG", "saved to document success : ");
//                                    requireActivity().getSupportFragmentManager().beginTransaction()
//                                            .replace(R.id.frame_layout, new WorksFormFragment())
//                                            .commit();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to set data to the subcollection
                            Log.e("TAG", "saved to document failed : ");
                        }
                    });

        }

    }

    private static void mergeMaps(Map<String, Object> destination, Map<String, Object> source) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (destination.containsKey(key)) {
                Object existingValue = destination.get(key);
                if (existingValue instanceof List<?>) {
                    // If the existing value is a list, add the new value to the list
                    List<Object> mergedList = (List<Object>) existingValue;
                    mergedList.add(value);
                } else {
                    // If the existing value is not a list, create a new list and add both values
                    List<Object> mergedList = new ArrayList<>();
                    mergedList.add(existingValue);
                    mergedList.add(value);
                    destination.put(key, mergedList);
                }
            } else {
                // If the key is not present in the destination map, simply add the value
                destination.put(key, value);
            }
        }
    }

    // if i will want that all the index will be from 0 - Infinity

//    public static Map<Integer, Object> getAllRecipesMapWithIndex() {
//        Map<Integer, Object> recipes = new HashMap<>();
//        int keyIndex = 0;
//
//        keyIndex = mergeMap(recipes, keyIndex, getDinnerRecipesMap());
//        keyIndex = mergeMap(recipes, keyIndex, getLunchRecipesMap());
//        keyIndex = mergeMap(recipes, keyIndex, getBreakFastRecipesMap());
//        keyIndex = mergeMap(recipes, keyIndex, getFridayDinnerRecipesMap());
//
//        return recipes;
//    }
//
//    private static int mergeMap(Map<Integer, Object> destination, int startIndex, Map<String, Object> source) {
//        for (Map.Entry<String, Object> entry : source.entrySet()) {
//            destination.put(startIndex, entry.getValue());
//            startIndex++;
//        }
//        return startIndex;
//    }


    public void uploadTripsToDB() {
        uploadTripInAsiaDB();
        uploadTripInEuropeDB();
        uploadTripInUSDB();
        uploadTripInIsraelDB();
    }

    public void uploadTripInAsiaDB() {
        CollectionReference TripInAsia = db.collection("TripInAsia");
        Map<String, Object> trip_1 = new HashMap<>();
        trip_1.put("title", "Angkor Wat Sunrise Tour, Cambodia");
        trip_1.put("image", "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/73/d9/f5.jpg");
        trip_1.put("isFavorite", false);
        trip_1.put("difficulty", "Easy");
        trip_1.put("time", 240);
        trip_1.put("tripFeatures", "This iconic trek takes you through the heart of the Himalayas, offering breathtaking views of some of the world's highest peaks, including Mount Everest. You'll pass through Sherpa villages, experience the local culture, and explore ancient monasteries along the way.");
        TripInAsia.document("Angkor Wat Sunrise Tour, Cambodia").set(trip_1);

        Map<String, Object> trip_2 = new HashMap<>();
        trip_2.put("title", "Tokyo City Highlights Tour, Japan");
        trip_2.put("image", "https://storage.googleapis.com/tblv3_bucket_us/guides/63/63157/2023296005528641.jpg");
        trip_2.put("isFavorite", false);
        trip_2.put("difficulty", "Easy");
        trip_2.put("time", 480);
        trip_2.put("tripFeatures", "Discover the vibrant city of Tokyo on a guided tour that takes you to iconic landmarks such as the Tokyo Tower, Senso-ji Temple, and the bustling streets of Shibuya. Enjoy a mix of modern and traditional attractions in one of the world's most exciting cities.");
        TripInAsia.document("Tokyo City Highlights Tour, Japan").set(trip_2);

        Map<String, Object> trip_3 = new HashMap<>();
        trip_3.put("title", "Halong Bay Day Cruise, Vietnam");
        trip_3.put("image", "https://res.klook.com/image/upload/c_fill,w_750,h_560/q_80/w_80,x_15,y_15,g_south_west,l_Klook_water_br_trans_yhcmh3/activities/qmgtdjekctlyucr8itqw.jpg");
        trip_3.put("isFavorite", false);
        trip_3.put("difficulty", "Easy");
        trip_3.put("time", 480);
        trip_3.put("tripFeatures", "Explore the stunning karst landscapes of Halong Bay on a day cruise. You'll sail through emerald waters, visit hidden caves, and enjoy a delicious seafood lunch on board, all while surrounded by breathtaking natural beauty.");
        TripInAsia.document("Halong Bay Day Cruise, Vietnam").set(trip_3);

        Map<String, Object> trip_4 = new HashMap<>();
        trip_4.put("title", "Dubai Desert Safari, UAE");
        trip_4.put("image", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSC_FX7cVZkDftd0GHqpAXiVgdMV5cw4yi0Yg&s");
        trip_4.put("isFavorite", false);
        trip_4.put("difficulty", "Medium");
        trip_4.put("time", 90);
        trip_4.put("tripFeatures", "Experience the thrill of a desert safari in the Arabian Desert. This adventure includes dune bashing, camel riding, and a traditional Bedouin camp experience with dinner and cultural performances. It's a perfect mix of excitement and cultural immersion.");
        TripInAsia.document("Dubai Desert Safari, UAE").set(trip_4);
    }

    public void uploadTripInEuropeDB() {
        CollectionReference TripInEurope = db.collection("TripInEurope");
        Map<String, Object> trip_1 = new HashMap<>();
        trip_1.put("title", "Cinque Terre Hiking Tour, Italy");
        trip_1.put("image", "https://57hours.com/wp-content/uploads/2023/01/cinque-terre-hiking.jpg");
        trip_1.put("isFavorite", false);
        trip_1.put("difficulty", "Medium");
        trip_1.put("time", 480);
        trip_1.put("tripFeatures", "Explore the picturesque villages of Cinque Terre, perched on the rugged cliffs of the Italian Riviera. This hiking tour offers stunning coastal views, colorful villages, and the chance to sample delicious local cuisine. The trails connect the five charming villages, each with its own unique charm.");
        TripInEurope.document("Cinque Terre Hiking Tour, Italy").set(trip_1);

        Map<String, Object> trip_2 = new HashMap<>();
        trip_2.put("title", "Loch Ness and the Scottish Highlands, Scotland");
        trip_2.put("image", "https://www.touristengland.com/wp-content/uploads/2022/01/loch-ness-scotland-adobe-805.jpg");
        trip_2.put("isFavorite", false);
        trip_2.put("difficulty", "Easy");
        trip_2.put("time", 600);
        trip_2.put("tripFeatures", "Embark on a scenic tour of the Scottish Highlands, including a visit to the legendary Loch Ness. Enjoy the dramatic landscapes, historic castles, and quaint villages. This trip includes a comfortable coach ride with stops for photo opportunities and exploring iconic locations.");
        TripInEurope.document("Loch Ness and the Scottish Highlands, Scotland").set(trip_2);

        Map<String, Object> trip_3 = new HashMap<>();
        trip_3.put("title", "Cliffs of Moher Adventure, Ireland");
        trip_3.put("image", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGIlJoNqvH_H6_oHAZ5hGmcWNoJzSoUko-8A&s");
        trip_3.put("isFavorite", false);
        trip_3.put("difficulty", "Medium");
        trip_3.put("time", 540);
        trip_3.put("tripFeatures", "Discover the breathtaking Cliffs of Moher on this guided tour. The journey includes a coastal walk along the cliffs, offering spectacular ocean views and a chance to spot local wildlife. The trip combines nature, history, and the dramatic beauty of Ireland’s west coast.");
        TripInEurope.document("Cliffs of Moher Adventure, Ireland").set(trip_3);
    }

    public void uploadTripInUSDB() {
        CollectionReference TripInUS = db.collection("TripInUS");
        Map<String, Object> trip_1 = new HashMap<>();
        trip_1.put("title", "Central Park Walking Tour, New York City, New York");
        trip_1.put("image", "https://manhattanwalkingtour.com/wp-content/uploads/2020/05/Central-Park-Mall-Autumn-1.jpg");
        trip_1.put("isFavorite", false);
        trip_1.put("difficulty", "Easy");
        trip_1.put("time", 240);
        trip_1.put("tripFeatures", "Explore the iconic Central Park, a green oasis in the heart of Manhattan. This leisurely walking tour includes famous landmarks such as Bethesda Terrace, Bow Bridge, and Strawberry Fields. Perfect for those who want to enjoy a blend of nature and cityscapes without strenuous activity.");
        TripInUS.document("Central Park Walking Tour, New York City, New York").set(trip_1);

        Map<String, Object> trip_2 = new HashMap<>();
        trip_2.put("title", "Angel's Landing Hike, Zion National Park, Utah");
        trip_2.put("image", "https://wildlandtrekking.com/content/uploads/2023/03/Iconic-Angels-Landing-Ridge.jpg");
        trip_2.put("isFavorite", false);
        trip_2.put("difficulty", "Hard");
        trip_2.put("time", 480);
        trip_2.put("tripFeatures", "Known for its thrilling heights and breathtaking views, this hike is not for the faint of heart. The trail ascends 1,500 feet and includes narrow ridges with chains for support. The reward is a panoramic view of Zion Canyon, making it a bucket-list hike for adventure seekers.");
        TripInUS.document("Angel's Landing Hike, Zion National Park, Utah").set(trip_2);

        Map<String, Object> trip_3 = new HashMap<>();
        trip_3.put("title", "Sonoma Valley Wine Tour, California");
        trip_3.put("image", "https://media.tacdn.com/media/attractions-splice-spp-674x446/0e/ad/a1/2d.jpg");
        trip_3.put("isFavorite", false);
        trip_3.put("difficulty", "Easy");
        trip_3.put("time", 520);
        trip_3.put("tripFeatures", "Discover the beautiful vineyards of Sonoma Valley on a guided wine tour. Enjoy tastings at several renowned wineries, learn about the winemaking process, and savor a gourmet lunch. This relaxing trip combines scenic drives with delicious wine experiences.");
        TripInUS.document("Sonoma Valley Wine Tour, California").set(trip_3);

        Map<String, Object> trip_4 = new HashMap<>();
        trip_4.put("title", "Mount Monadnock Climb, New Hampshire");
        trip_4.put("image", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg9NGocZVXCwnAG0kLCaXzu0UlPgA6tOdtGROCjp3D4UJ-27pmslGjfoXyl1l9mP_hWZlDErEbJg8zZtg8lwgOE11hTqYtj2Zyws7juuX4LBD8ms9-KnIrCr1BSNYXAgT5c7nI4KzNrd62MlTjGthUGl1S46ll8YQ-ypez-1QIwx68i-Y6WZgX1pvVcrQ/s1600/IMG_1925.jpg");
        trip_4.put("isFavorite", false);
        trip_4.put("difficulty", "Medium");
        trip_4.put("time", 480);
        trip_4.put("tripFeatures", "Tackle one of the most climbed mountains in the world with a day hike up Mount Monadnock. The trails offer a variety of terrains, and the summit provides stunning views of the surrounding countryside. It's a great choice for hikers looking for a manageable challenge.");
        TripInUS.document("Mount Monadnock Climb, New Hampshire").set(trip_4);
    }

    public void uploadTripInIsraelDB() {
        CollectionReference TripInIsrael = db.collection("TripInIsrael");
        Map<String, Object> trip_1 = new HashMap<>();
        trip_1.put("title", "Masada Sunrise Hike");
        trip_1.put("image", "https://www.eggedtours.com/wp-content/uploads/2023/03/Masada5.jpg.webp");
        trip_1.put("isFavorite", false);
        trip_1.put("difficulty", "Medium");
        trip_1.put("time", 180);
        trip_1.put("tripFeatures", "Hike up the ancient fortress of Masada before dawn to witness a breathtaking sunrise over the Judean Desert. This historical site offers stunning views and insights into the dramatic history of King Herod’s palace and the Jewish-Roman wars.");
        TripInIsrael.document("Masada Sunrise Hike").set(trip_1);

        Map<String, Object> trip_2 = new HashMap<>();
        trip_2.put("title", "Tel Aviv Urban Bicycle Tour");
        trip_2.put("image", "https://static.israel21c.org/www/uploads/2017/09/shutterstock_549899269-1000x657.jpg");
        trip_2.put("isFavorite", false);
        trip_2.put("difficulty", "Easy");
        trip_2.put("time", 180);
        trip_2.put("tripFeatures", "Explore the vibrant city of Tel Aviv on a guided bicycle tour. Pedal through the bustling streets, enjoy the coastal promenade, visit famous landmarks like the White City, and experience the city's unique blend of modernity and history.");
        TripInIsrael.document("Tel Aviv Urban Bicycle Tour").set(trip_2);

        Map<String, Object> trip_3 = new HashMap<>();
        trip_3.put("title", "Ein Gedi Nature Reserve Hike");
        trip_3.put("image", "https://i0.wp.com/www.touristisrael.com/wp-content/uploads/2012/03/Ein-Gedi.jpg?resize=1024%2C683&ssl=1");
        trip_3.put("isFavorite", false);
        trip_3.put("difficulty", "Medium");
        trip_3.put("time", 120);
        trip_3.put("tripFeatures", "Discover the natural beauty of the Ein Gedi Nature Reserve. This hike takes you through lush oases, waterfalls, and the chance to spot wildlife like ibexes and rock hyraxes. Enjoy the serene environment and refreshing natural pools.");
        TripInIsrael.document("Ein Gedi Nature Reserve Hike").set(trip_3);

        Map<String, Object> trip_4 = new HashMap<>();
        trip_4.put("title", "Rappelling in the Ramon Crater");
        trip_4.put("image", "https://www.deepdesertisrael.com/wp-content/uploads/2019/10/20160609_172522-1.jpg");
        trip_4.put("isFavorite", false);
        trip_4.put("difficulty", "Hard");
        trip_4.put("time", 240);
        trip_4.put("tripFeatures", "Experience the thrill of rappelling down the cliffs of the Ramon Crater, one of Israel’s most stunning geological formations. This adventure offers an adrenaline rush along with spectacular views of the Negev Desert’s unique landscapes.");
        TripInIsrael.document("Rappelling in the Ramon Crater").set(trip_4);

    }

    public void updateTrip(String category, Trip trip) {
        CollectionReference collectionRef = db.collection(category);
        if (trip != null) {
            String documentId = trip.getTitle();
            Log.d(TAG, "updateRecipe: " + trip);
            collectionRef.document(documentId).set(trip)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Update successful
                            Log.d("TAG", "updateWorkOrder onSuccess: ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Update failed
                            Log.d("TAG", "updateWorkOrder onFailure: ");
                        }
                    });
        }
    }


    public void readFromDB(String category, DataRetrievedListener listener) {

        CollectionReference collectionRef = db.collection(category);

        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                ArrayList<Trip> tripList = new ArrayList<>();

                for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                    Trip trip = documentSnapshot.toObject(Trip.class);
                    tripList.add(trip);
                }
                listener.onDataRetrieved(tripList);

                // Do something with the recipes ArrayList here
                // For example, you can pass it to another method or update your UI
            }
        });

    }

    public void readFromDBUserFavorites(DataRetrievedListener listener) {
        ArrayList<Trip> tripList = new ArrayList<>();

        DocumentReference userDocumentRef = db.collection(Constants.DBKeys.USERS).document(user.getUid());
        CollectionReference collectionRef = userDocumentRef.collection("favorites");
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Trip trip = documentSnapshot.toObject(Trip.class);
                    tripList.add(trip);
                }
                listener.onDataRetrieved(tripList);
            }
        });

    }

    public void setUser(FirebaseUser currentUser) {
        this.user = currentUser;
    }

    public String addNewDocument(Trip trip) {
        DocumentReference userDocumentRef = db.collection(Constants.DBKeys.USERS).document(user.getUid());
        CollectionReference collectionRef = userDocumentRef.collection("favorites");
        DocumentReference newDocumentRef = collectionRef.document();
        String documentId = newDocumentRef.getId(); // Get the auto-generated document ID
//        recipe.setFavorite(true);
        newDocumentRef.set(trip)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        return documentId;
    }

    public void deleteDocuments(Trip trip) {
        DocumentReference userDocumentRef = db.collection(Constants.DBKeys.USERS).document(user.getUid());
        CollectionReference collectionRef = userDocumentRef.collection("favorites");
        Query query = collectionRef.whereEqualTo("title", trip.getTitle());
        query.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        // Iterate through the documents returned by the query
                        for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                            // Delete each document
                            documentSnapshot.getReference().delete();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                    }
                });
    }
}

