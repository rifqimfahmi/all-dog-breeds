# All dog breeds
An android application provide you with all the dog breed available in the world
## About
This is an open source application that list all of dog breeds and its corresponding photos. For more detail, you can download the application on the Play Store [here](https://play.google.com/store/apps/details?id=com.rifqimfahmi.alldogbreeds). This application use several API that are:
- [Dog API](https://dog.ceo/dog-api/) - an open source API for dog breed
- [GIPHY API](https://developers.giphy.com/) - an API to get funny dog gif

Feel free to rate the app and tell your opinion about it. If there is any improvement, bug, or request for new features, you can create new issues.

This project use MVP architecture. I use my [generator-mvp-kotlin](https://github.com/zcabez/generator-mvp-kotlin) library to generate all the library, basic class, folder structure that i need. You can also use the generator i made, go check it out.
## Note to fork
I didn't include the string resource file that hold my GIPHY API KEY to git in order to prevent my api key abuse. if you fork this project and you want to develop it for your own application. you have to create new string resource:
1. create **secrets.xml** in your resource folder. in *values* folder to be precise
2. add new string resource with the name of **giphy_api_key**
```
<resources>
    <string name="giphy_api_key">MY_GIPHY_API_KEY</string>
</resources>
```
## License

Apache-2.0 © [Rifqi Mulya Fahmi](https://www.linkedin.com/in/rifqi-mulya-fahmi-66b02a10b/)

```
Copyright 2018 Rifqi Mulya Fahmi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
