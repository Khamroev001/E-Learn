package khamroev001.e_learn.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class API private constructor(context: Context) {
    private val shared = context.getSharedPreferences("data",  Context.MODE_PRIVATE)
    private val edit = shared.edit()
    private val gson = Gson()

    private val mentorsString = "mentors"
    private val usersString = "users"
    private val coursesString = "courses"
    private val reviewsString = "reviews"
    private val bookmarkString = "bookmarks"


    companion object {
        private var instance: API? = null
        fun newInstance(context: Context): API {
            if (instance == null) {
                instance = API(context)
                println("API created")
            }
            instance!!.hasData()
            return instance!!
        }
    }

    fun getMentors(): ArrayList<Mentor> {
        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        val data: String = shared.getString(mentorsString, "")!!
        val typeToken = object : TypeToken<ArrayList<Mentor>>() {}.type
        if (data == "") return ArrayList()
        println(data)
        return gson.fromJson<ArrayList<Mentor>?>(data, typeToken)
    }

    fun getUsers(): ArrayList<User> {

        val data: String = shared.getString(usersString, "")!!
        val typeToken = object : TypeToken<ArrayList<User>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }

    fun getCourses(): ArrayList<Course> {
        val data: String = shared.getString(coursesString, "")!!
        val typeToken = object : TypeToken<ArrayList<Course>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }


    fun getCourses(name: String): ArrayList<Course> {
        val data: String = shared.getString(coursesString, "")!!
        val typeToken = object : TypeToken<ArrayList<Course>>() {}.type
        if (data == "") return ArrayList()
        val a:ArrayList<Course> =  gson.fromJson(data, typeToken)
        val new = arrayListOf<Course>()
        for (i in a){
            if (i.name.toLowerCase().contains(name.toLowerCase())){
                new.add(i)
            }
        }
        return new
    }

    fun getCourses(category: Category): ArrayList<Course> {
        val data: String = shared.getString(coursesString, "")!!
        val typeToken = object : TypeToken<ArrayList<Course>>() {}.type
        if (data == "") return ArrayList()
        val a:ArrayList<Course> =  gson.fromJson(data, typeToken)
        val new = arrayListOf<Course>()
        for (i in a){
            if (i.category == category){
                new.add(i)
            }
        }
        return new
    }
    fun getCourses(mentor: Mentor): ArrayList<Course> {
        val data: String = shared.getString(coursesString, "")!!
        val typeToken = object : TypeToken<ArrayList<Course>>() {}.type
        if (data == "") return ArrayList()
        val a:ArrayList<Course> =  gson.fromJson(data, typeToken)
        val new = arrayListOf<Course>()
        for (i in a){
            if (i.mentor == mentor){
                new.add(i)
            }
        }
        return new
    }

    fun getReviews(): ArrayList<Review> {
        val data: String = shared.getString(reviewsString, "")!!
        val typeToken = object : TypeToken<ArrayList<Review>>() {}.type
        if (data == "") return ArrayList()
        return gson.fromJson(data, typeToken)
    }
    fun getReviews(mentor: Mentor): ArrayList<Review> {
        val data: String = shared.getString(reviewsString, "")!!
        val typeToken = object : TypeToken<ArrayList<Review>>() {}.type
        if (data == "") return ArrayList()
        val a: ArrayList<Review> = gson.fromJson(data, typeToken)
        val new  = arrayListOf<Review>()
        val courses = getCourses(mentor)
        for (i in a){
            if (courses.contains(i.course)){
                new.add(i)
            }
        }
        return new
    }
    fun getReviews(course: Course): ArrayList<Review> {
        val data: String = shared.getString(reviewsString, "")!!
        val typeToken = object : TypeToken<ArrayList<Review>>() {}.type
        if (data == "") return ArrayList()
        val a: ArrayList<Review> = gson.fromJson(data, typeToken)
        val new  = arrayListOf<Review>()
        for (i in a){
            if (i.course == course){
                new.add(i)
            }
        }
        return new
    }

    fun getRating(course: Course, reviewS: ArrayList<Review>): Double {
        if (reviewS.isEmpty()) return 0.0
        val myReviews = arrayListOf<Review>()
        for (i in reviewS) {
            if (i.course == course) {
                myReviews.add(i)
            }
        }
        var sum = 0
        var c = 0
        for (i in myReviews) {
            sum += i.score
            c++
        }
        if (sum == 0) return 0.0
        return sum / c.toDouble()
    }

    fun getStudentsCount(course: Course): Int {
        // TODO : WRITE THE CODE
        return 22345
    }
    fun getStudentsCount(mentor: Mentor): Int {
        // TODO : WRITE THE CODE
        return 22345
    }

    fun getBookmarks(): ArrayList<Course> {
        val data: String = shared.getString(bookmarkString, "")!!
        if (data == "") {
            return ArrayList()
        }
        val typeToken = object : TypeToken<ArrayList<Course>>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun updateBookmarks(course: Course): Boolean {
        Log.d("TAG1", "1: ")
        val bookmarks = getBookmarks()
        Log.d("TAG1", bookmarks.toString())
        return if (bookmarks.contains(course)) {
            bookmarks.remove(course)
            edit.putString(bookmarkString, gson.toJson(bookmarks)).apply()
            false
        } else {
            Log.d("TAG1", "add bookmark: ")
            bookmarks.add(course)
            edit.putString(bookmarkString, gson.toJson(bookmarks)).apply()
            true
        }
    }


    fun hasData() {

        val user1 = User(
            "Ali1202",
            "ali@email.com",
            "11111111",
            "Ali",
            "Aliyev",
            true,
            "https://m.timesofindia.com/photo/83890830/83890830.jpg",
            arrayListOf()
        )
        val user2 = User(
            "Benny",
            "benny@email.com",
            "11111111",
            "Benny",
            "Spanbauer",
            true,
            "https://img.freepik.com/free-photo/portrait-smiling-charming-young-man-grey-t-shirt-standing-against-plain-background_23-2148213406.jpg?w=360",
            arrayListOf()
        )
        val user3 = User(
            "Freida1",
            "freida@email.com",
            "11111111",
            "Freida",
            "Varnes",
            false,
            "https://media.istockphoto.com/id/1348830922/photo/shot-of-an-attractive-young-businesswomen-standing-alone-outside-with-her-arms-folded.jpg?b=1&s=170667a&w=0&k=20&c=sIsWIHDysN_AI0QYEqfxkySmTVspbtDP-OJgfdBk1pM=",
            arrayListOf()
        )
        val user4 = User(
            "ClintonBest",
            "clioton@email.com",
            "11111111",
            "Clinton",
            "Mcclure",
            true,
            "https://img.freepik.com/free-photo/waist-up-portrait-handsome-serious-unshaven-male-keeps-hands-together-dressed-dark-blue-shirt-has-talk-with-interlocutor-stands-against-white-wall-self-confident-man-freelancer_273609-16320.jpg?w=2000",
            arrayListOf()
        )
        val user5 = User(
            "Chieko",
            "chieko@email.com",
            "11111111",
            "Chieko",
            "Chute",
            false,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_PdJ0Nw5ADvr9NzVeuogCLnxzu_XJGYn20tShrMXDZvVqQgPUx3z6b_Ubz-DCWsZc4ts&usqp=CAU",
            arrayListOf()
        )
        val user6 = User(
            "Doniyor",
            "doniyor@email.com",
            "11111111",
            "Doniyor",
            "Ziyodov",
            true,
            "https://media.istockphoto.com/id/1048695348/photo/cheerful-young-man-pointing-at-himself.jpg?s=612x612&w=0&k=20&c=36UH2zZDheSZM4FykeDBVYCpi9MtibK6HYptC2B0aNs=",
            arrayListOf()
        )
        val user7 = User(
            "Shamsiddin",
            "shamsiddin@email.com",
            "11111111",
            "Shamsiddin",
            "Tohirov",
            true,
            "https://media.istockphoto.com/id/1200677760/photo/portrait-of-handsome-smiling-young-man-with-crossed-arms.jpg?s=612x612&w=0&k=20&c=g_ZmKDpK9VEEzWw4vJ6O577ENGLTOcrvYeiLxi8mVuo=",
            arrayListOf()
        )
        val user8 = User(
            "Hasan007",
            "hasan@email.com",
            "11111111",
            "Hasan",
            "Bo'ronov",
            true,
            "https://image.cnbcfm.com/api/v1/image/106930629-1629399630371-gettyimages-494691340-87856868.jpeg?v=1629399760",
            arrayListOf()
        )
        val user9 = User(
            "Lucy$$$",
            "lucy@email.com",
            "11111111",
            "Lucy",
            "Brown",
            false,
            "https://www.shutterstock.com/image-photo/confident-smiling-female-employee-260nw-339668693.jpg",
            arrayListOf()
        )
        val user10 = User(
            "Linda23",
            "linda@email.com",
            "11111111",
            "Linda",
            "Bauer",
            false,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtVQmNf7ZXkCNZu9-seEv2_dsQtvBgdDyx0Db-U1zaoOibxjbTZYzXRW8vgwUfAWOrrtw&usqp=CAU",
            arrayListOf()
        )

        val mentor1 = Mentor(
            "Teador",
            "Snowden",
            "3D Designer",
            "https://img.freepik.com/premium-photo/smiling-man-with-crossed-arms_23-2147574143.jpg"
        )
        val mentor2 = Mentor(
            "Jacob",
            "Snowden",
            "Business manager",
            "https://img.freepik.com/free-photo/close-up-confident-male-employee-white-collar-shirt-smiling-camera-standing-self-assured-against-studio-background_1258-26761.jpg"
        )
        val mentor3 = Mentor(
            "Claire",
            "Snowden",
            "Flutter developer",
            "https://media.istockphoto.com/id/1324786380/photo/young-handsome-smiling-man-in-brown-shirt-and-glasses-feeling-confident-isolated-on-gray.jpg?s=612x612&w=0&k=20&c=EWqUQzPW-4jH8rri6eQAeomVfeizC2ead7YCl28KhXU=    "
        )
        val mentor4 = Mentor(
            "Wade",
            "Snowden",
            "UI freelancer",
            "https://bcbstwelltuned.com/wp-content/uploads/2016/12/employeementalhealth.jpg"
        )
        val mentor5 = Mentor(
            "Katy",
            "Snowden",
            "AI developer in Google",
            "https://unboxed-web-production.s3.amazonaws.com/media/images/female-employee-taking-part-in-a-virtual-ba.max-1028x464_cWtEF3e.jpg"
        )

        val course1 = Course(
            "3D design illustration",
            Category.ThreeDDESIGN,
            "https://cdn.dribbble.com/users/4835348/screenshots/16676475/media/41b0fdefcfa0608fa9b48aa48a8ed957.png?compress=1&resize=400x300",
            arrayListOf(48),
            2,
            30,
            true,
            mentor1,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course2 = Course(
            "3D design",
            Category.ThreeDDESIGN,
            "https://c.s-microsoft.com/en-us/CMSImages/All-in-One_1040x585.jpg?version=15692d40-cc47-8fe4-2366-334f32795879",
            arrayListOf(80, 48),
            2,
            15,
            false,
            mentor1,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course3 = Course(
            "CRM management",
            Category.BUSINESS,
            "https://blog.kms-solutions.asia/hubfs/UI%20UX%20DESIGN%20FOR%20SUPER%20APPS.jpg",
            arrayListOf(20),
            2,
            30,
            true,
            mentor2,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course4 = Course(
            "Flutter mobile",
            Category.MOBILE,
            "https://cdn.dribbble.com/users/4835348/screenshots/16676475/media/41b0fdefcfa0608fa9b48aa48a8ed957.png?compress=1&resize=400x300",
            arrayListOf(48),
            2,
            24,
            false,
            mentor3,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course5 = Course(
            "Management",
            Category.BUSINESS,
            "https://c.s-microsoft.com/en-us/CMSImages/All-in-One_1040x585.jpg?version=15692d40-cc47-8fe4-2366-334f32795879",
            arrayListOf(45, 15),
            3,
            30,
            true,
            mentor2,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course6 = Course(
            "Flutter desktop",
            Category.MOBILE,
            "https://blog.kms-solutions.asia/hubfs/UI%20UX%20DESIGN%20FOR%20SUPER%20APPS.jpg",
            arrayListOf(48),
            2,
            30,
            true,
            mentor3,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course7 = Course(
            "Website UI/UX",
            Category.UIUX,
            "https://c.s-microsoft.com/en-us/CMSImages/All-in-One_1040x585.jpg?version=15692d40-cc47-8fe4-2366-334f32795879",
            arrayListOf(85, 50),
            2,
            0,
            false,
            mentor4,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course8 = Course(
            "AI for complete beginner",
            Category.AI,
            "https://cdn.dribbble.com/users/4835348/screenshots/16676475/media/41b0fdefcfa0608fa9b48aa48a8ed957.png?compress=1&resize=400x300",
            arrayListOf(48),
            2,
            15,
            true,
            mentor5,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course9 = Course(
            "AIa (advanced)",
            Category.AI,
            "https://blog.kms-solutions.asia/hubfs/UI%20UX%20DESIGN%20FOR%20SUPER%20APPS.jpg",
            arrayListOf(60, 30),
            2,
            30,
            true,
            mentor5,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )
        val course10 = Course(
            "UI/UX",
            Category.UIUX,
            "https://cdn.dribbble.com/users/4835348/screenshots/16676475/media/41b0fdefcfa0608fa9b48aa48a8ed957.png?compress=1&resize=400x300",
            arrayListOf(75),
            2,
            45,
            false,
            mentor4,
            "In this course you will learn what Artificial Intelligence (AI) is, explore use cases and applications of AI, understand AI concepts and terms like machine learning, deep learning and neural networks.  You will also demonstrate AI in action with a mini project."
        )

        val reviews = arrayListOf(
            Review("Zo'r, malades", 3, user1, course1),
            Review("Zo'r, malades", 4, user2, course1),
            Review("Zo'r, malades", 5, user3, course1),
            Review("Zo'r, malades", 4, user4, course1),
            Review("Zo'r, malades", 3, user5, course2),
            Review("Zo'r, malades", 4, user6, course2),
            Review("Zo'r, malades", 2, user7, course2),
            Review("Zo'r, malades", 4, user8, course3),
            Review("Zo'r, malades", 1, user9, course3),
            Review("Zo'r, malades", 4, user10, course3),
            Review("Zo'r, malades", 5, user1, course3),
            Review("Zo'r, malades", 4, user2, course4),
            Review("Zo'r, malades", 3, user3, course4),
            Review("Zo'r, malades", 4, user4, course4),
            Review("Zo'r, malades", 2, user5, course4),
            Review("Zo'r, malades", 4, user6, course6),
            Review("Zo'r, malades", 4, user7, course6),
            Review("Zo'r, malades", 5, user8, course6),
            Review("Zo'r, malades", 4, user9, course6),
            Review("Zo'r, malades", 5, user10, course7),
            Review("Zo'r, malades", 4, user1, course7),
            Review("Zo'r, malades", 3, user2, course7),
            Review("Zo'r, malades", 4, user3, course7),
            Review("Zo'r, malades", 2, user4, course7),
            Review("Zo'r, malades", 4, user5, course8),
            Review("Zo'r, malades", 1, user6, course8),
            Review("Zo'r, malades", 5, user7, course9),
            Review("Zo'r, malades", 4, user8, course9),
            Review("Zo'r, malades", 3, user9, course9),
            Review("Zo'r, malades", 4, user10, course9),
            Review("Zo'r, malades", 4, user1, course9),
            Review("Zo'r, malades", 4, user2, course9),
            Review("Zo'r, malades", 5, user3, course10),
            Review("Zo'r, malades", 2, user4, course10),
            Review("Zo'r, malades", 5, user5, course10),
        )
        val users =
            arrayListOf(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10)
        val mentors = arrayListOf(mentor1, mentor2, mentor3, mentor4, mentor5)
        val courses = arrayListOf(
            course1,
            course2,
            course3,
            course4,
            course5,
            course6,
            course7,
            course8,
            course9,
            course10
        )

        edit.putString(reviewsString, gson.toJson(reviews)).commit()
        edit.putString(usersString, gson.toJson(users)).commit()
        edit.putString(mentorsString, gson.toJson(mentors)).commit()
        edit.putString(coursesString, gson.toJson(courses)).commit()

    }

}