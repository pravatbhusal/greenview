<img src="https://raw.githubusercontent.com/Shadowsych/greenview/master/app/src/main/res/mipmap/ic_launcher_round.png" width="100" height="100" />

### Google Play Store: https://play.google.com/store/apps/details?id=com.shadowsych.greenview  
### DevPost: https://devpost.com/software/greenview-ut2ryo

# Developers
- Pravat Bhusal (Java Client-side Developer)
- Rohith Rajashekarbabu (PHP & MySQL Server-side Developer)
- Michael Kasman (Team Coordinator)
- Mostafa Amir (Graphics Designer)
- Hrishikesh Rajashekarbabu (Android Studio Designer)

# Inspiration
Pollution is a detrimental force to environmental sustainability and human health, which releases dioxins in the atmosphere that creates pollution in the air, water, and soil. The increasing rates of pollution and smog days from burning trash (plastic, paper, glass, etc.) is alarming to our community.

# Functionality
GreenView is a mobile app which will allow users to take pictures of any items and determine whether the item is recyclable or not.

# Technologies
- Java (Client-side & Android Studio)
- PHP (Server-side)
- MySQL (Database)
- XML (Android Activities & Manifest)
- PhotoShop (Graphics)

# Server-side Configuration
1. Clone or download the .zip of this repository
2. Inside the main/php folder, place all php files into a web-server
3. Inside the LeaderboardActivity.java file, change all files in the HTTPRequest() methods to your web-server's PHP files
4. Set-up the Facebook API (Documentation Here: https://developers.facebook.com/docs/)
5. Set-up the Clairfai API (Documentation Here: https://clarifai.com/developer/guide/)
6. Inside the LeaderboardActivity.java file, change the API_KEY_HERE to your Clairfai API Key
7. Inside the values/string.xml, change the FACEBOOK_API_HERE to your Facebook API Key
7. Finished!

# Database Configuration
1. Inside the main/php folder, export the database.sql file into your MySQL database 
2. Inside the main/php folder, open the dbconnection.php file and configure the variables to your database credentials
3. Finished!

# Generating Facebook Release KeyHash
1. Open the application in the Google Play Developer account  
2. Click "Release Management" then click "App signing"  
3. Copy and paste the SHA-1 certificate fingerprint  
`(for example: 3B:DA:A0:5B:4F:35:71:02:4E:27:22:B9:AC:B2:77:2F:9D:A9:9B:D9)`  
4. Now create a bytearray for the certificate and then convert it into base64 in the onCreate() function in the MainActivity.java  
`byte[] sha1 = {
    0x3B, (byte)0xDA, (byte)0xA0, 0x5B, 0x4F, 0x35, 0x71, 0x02, 0x4E, 0x27, 0x22, (byte)0xB9, (byte)0xAc, (byte)0xB2, 0x77, 0x2F, (byte)0x9D, (byte)0xA9, (byte)0x9B, (byte)0xD9
};`  
`Log.e("keyhash", Base64.encodeToString(sha1, Base64.NO_WRAP));`
5. Copy the keyhash from the LogCat and then go to developers.facebook.com/app then go to "Settings" then paste your keyhash  
6. Finished!
