# HeroCards
Recyclerview yapısında kahramanları adlarını ve quirklerini gösterdim.
Kahramana tıklandığında kart sayfası açılıyor ve tıklanan kahramanın bilgileri gösteriliyor.
Verileri kendim oluşturdum. https://github.com/karacloud1/heroes-json reposunun içerisine hero.json dosyası açtım ve değerleri girdim. 
Retrofit kullanmak için yazdığım json dosyasının raw görünümündeki linkten yararlandım. Program ilk açılışta verileri internetten çekiyor.
Çektiği verileri room ile sqlite database'ine kayıt ediyor. Kayıt zamanını sharedpreferences ile telefona kayıt ediyor. 
Eğer son kayıttan sonra 10 dakika geçerse tekrar verileri internetten çekiyor. Ayrıca swiperefreshlayout ile verilerin internetten çekilmesi sağlanabiliyor.
Bir activity 2 adet fragmenttan oluşuyor. Navigation kullanarak list ekranından seçilen kahramanın id'si card ekranına aktarılıyor.
Program MVVM mimarisinde geliştirildi.
Retrofit
Room
Data Binding ve Glide kullanıldı.

![recycler1](https://user-images.githubusercontent.com/83123957/129726059-c0d69ff1-b276-4873-ab6f-3ff466ac5be0.PNG)
![recycler2](https://user-images.githubusercontent.com/83123957/129726063-23cae040-6d40-426f-a7de-1cc8d668f72a.PNG)
![recycler3](https://user-images.githubusercontent.com/83123957/129726064-b25ee42e-7375-4acc-b19a-42dc9f23a947.PNG)
![recycler4](https://user-images.githubusercontent.com/83123957/129726067-86687320-93f1-4611-85e7-bd77630d888d.PNG)

![herocard1](https://user-images.githubusercontent.com/83123957/129726082-af7c8484-cec2-4033-a6d9-5e8174f0aaff.PNG)
![herocard2](https://user-images.githubusercontent.com/83123957/129726083-6fff0db8-102a-4f3d-9d0a-9db40bc9a222.PNG)
![herocard3](https://user-images.githubusercontent.com/83123957/129726086-b0a92ffd-94d5-43d1-a04c-2619f1d79ee7.PNG)
