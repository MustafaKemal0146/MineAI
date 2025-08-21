# MineAI - Minecraft PaperMC 1.21.8 AI Sohbet Eklentisi

MineAI, Minecraft PaperMC 1.21.8 sunucuları için geliştirilmiş, Google AI Studio (Gemini) API ile entegre çalışan bir yapay zeka sohbet eklentisidir. Oyuncular, oyun içinden çeşitli komutlarla yapay zeka ile sohbet edebilir, model seçebilir ve API anahtarını kolayca yönetebilir.

## Kurulum

1. `mineai-1.0.5.jar` dosyasını Minecraft sunucunuzun `plugins` klasörüne atın.
2. Sunucunuzu yeniden başlatın veya `/reload` komutunu kullanın.
3. Oyun içinde aşağıdaki komutları kullanarak yapılandırmayı tamamlayın.

## Komutlar ve Kullanım

### 1. Yardım Komutu
```
/aihelp
```
Oyun içi yardım ve komut listesini gösterir.

![/aihelp](/images/aihelp.png)

---

### 2. API Anahtarı Tanımlama
```
/api <API_ANAHTARINIZ>
```
Google AI Studio'dan ücretsiz API anahtarı alıp buraya girin. Bağlantı testi otomatik yapılır.

![/api](/images/api.png)

---

### 3. Model Seçimi
```
/model
/model <model_ismi>
```
Kullanılabilir Gemini modellerini listeler veya seçer.

![/model](/images/model.png)

---

### 4. Yapay Zeka ile Sohbet
```
/ai <mesajınız>
```
Yapay zeka ile sohbet başlatır. Yanıtlar arasında ayırıcı çizgi bulunur.

![/ai yanıtı](/images/aiyanıt.png)

---

## Özellikler
- Google AI Studio (Gemini) API ile tam entegre
- Kişiye özel API anahtarı ve model seçimi
- Sohbet geçmişi desteği
- Oyun içi yardım ve hata mesajları
- Yanıtlar arasında ayırıcı çizgi ile okunaklı sohbet

## Geliştirici Notları
- Tüm kaynak kodları `github/codes` klasöründe bulabilirsiniz.
- Eklenti Java 21 ile derlenmiştir, PaperMC 1.21.8 ile tam uyumludur.
- Dış kütüphaneler (org.json) jar dosyasına gömülüdür (shaded).

## Hızlı Başlangıç
1. [Google AI Studio](https://aistudio.google.com/app/apikey) adresinden ücretsiz API anahtarınızı alın.
2. Sunucuda `/api <anahtar>` ile anahtarınızı girin.
3. `/model` ile model seçin veya varsayılanı kullanın.
4. `/ai <mesaj>` ile yapay zeka ile konuşmaya başlayın!

---

**MineAI ile Minecraft sunucunuzda yapay zeka deneyimini başlatın!**
