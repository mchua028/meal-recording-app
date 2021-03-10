# CZ2006-Project



## How to download directly into Android Studio
Note: I have not tried this myself because I only uploaded before
1. File>Settings>Git
    - Add "Path to Git executable: *the suggested path*" at the top
2. File>Settings>Github
    - Login to your account:
      - Go to your webpage with your account logged in
      - Settings>Developer settings>Personal Access Token
      - Copy your Personal Access Token into the Android Studio Github settings thing
3. File>New>Project From Version Control...
  - Simply enter Repository URL 
  - Click Clone
4. END



## How to upload to Github from Android Studio
After logging in and doing all of the above steps 1 and 2,
1. At the top right bar on your Android Studio screen, you can see Git followed by an arrow and a tick
2. Click the tick to commit, select relevant files and add a really short Commit message (e.g. Changed *something*)
  - Click Commit at the bottom left
3. VCS>Git>Push
  - Click the blue underlined thingy (Define remote or something) beside master->*the-blue-underlined-thing*
  - Add this repository's url: https://github.com/potassiumnitrate/CZ2006-Project/
  - Click Push
4. END idk if u need to do anything after that...



## Emulator I used to run app: 
Pixel 3a API 30x86



## Important Java classes
directory: MealTracker/app/src/main/java/com.example.mealtracker
- files were used to connect the various fragments (pages):
  - main thing to connect all fragments: MainActivity
  - fragments: accountAndSettings, foodRecommendations, myCalories, myMeals 
- files used for buttons etc for login and register:
  - GoogleLogin, Login, LoginOrRegister, Register
  
  
  
## xml classes - for aesthetic purposes, shifting around/adding/deleting attributes and textboxes
directory: MealTracker/app/src/main/res/menu
-  nav_menu.xml (purpose: navigation/sidebar)

directory: MealTracker/app/src/main/res/layout (has one xml class for each of the above java class)
- activity_google_login.xml, activity_login.xml, activity_login_or_register.xml, activity_register.xml
- activity_main.xml, fragment_account_and_settings.xml, fragment_food_recommendations.xml, fragment_my_calories.xml, fragment_my_meals.xml
- nav_header_layout.xml (purpose: navigation/sidebar)

directory: MealTracker/app/src/main/res/values
- colors.xml, strings.xml, styles.xml, themes.xml
