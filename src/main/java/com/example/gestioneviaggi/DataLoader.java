package com.example.gestioneviaggi;

import com.example.gestioneviaggi.model.Dipendente;
import com.example.gestioneviaggi.model.Viaggio;
import com.example.gestioneviaggi.model.Prenotazione;
import com.example.gestioneviaggi.enumration.TipoStato;
import com.example.gestioneviaggi.repository.DipendenteRepository;
import com.example.gestioneviaggi.repository.ViaggioRepository;
import com.example.gestioneviaggi.repository.PrenotazioneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;
    private final PrenotazioneRepository prenotazioneRepository;

    public DataLoader(DipendenteRepository dipendenteRepository, ViaggioRepository viaggioRepository, PrenotazioneRepository prenotazioneRepository) {
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
        this.prenotazioneRepository = prenotazioneRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔹 بارگذاری داده‌های تستی بدون ذخیره اولیه...");

        // 1️⃣ ایجاد یک کارمند تستی
        Dipendente dipendente1 = new Dipendente("jdoe", "John", "Doe", "jdoe@example.com");

        // 2️⃣ ایجاد یک سفر کاری تستی
        Viaggio viaggio1 = new Viaggio("Milano", LocalDate.of(2025, 3, 15), TipoStato.IN_PROGRAMMA);

        // 3️⃣ ابتدا Dipendente و Viaggio را ذخیره کنیم تا ID داشته باشند
        dipendente1 = dipendenteRepository.save(dipendente1);
        viaggio1 = viaggioRepository.save(viaggio1);

        // 4️⃣ ایجاد یک رزرو تستی و ذخیره آن به‌صورت جداگانه
        Prenotazione prenotazione1 = new Prenotazione();
        prenotazione1.setDataPrenotazione(LocalDate.of(2025, 3, 10));

        // 5️⃣ ابتدا `Prenotazione` را ذخیره کن تا یک `ID` بگیرد
        prenotazione1 = prenotazioneRepository.save(prenotazione1);

        // 6️⃣ حالا `Prenotazione` را به `Viaggio` و `Dipendente` اضافه کن
        viaggio1.getListaPrenotazioni().add(prenotazione1);
        dipendente1.getListaPrenotazioni().add(prenotazione1);

        // 7️⃣ ذخیره دوباره `Viaggio` و `Dipendente` تا کلیدهای خارجی مقدار بگیرند
        viaggioRepository.save(viaggio1);
        dipendenteRepository.save(dipendente1);

        System.out.println("✅ داده‌های تستی با موفقیت ذخیره شدند!");
    }


}
