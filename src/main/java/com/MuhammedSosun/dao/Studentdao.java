package com.MuhammedSosun.dao;

import com.MuhammedSosun.Utils.ERole;
import com.MuhammedSosun.Utils.EStudentType;
import com.MuhammedSosun.dto.StudentDto;
import com.MuhammedSosun.exception.StudentNotFoundException;
import com.MuhammedSosun.Utils.SpecialColor;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Studentdao implements IDaoGenerics<StudentDto>{
    private ArrayList<StudentDto> studentDtoList;
    int maxId = 0;
    private static final String FILE_NAME = "student.txt";

    Scanner scanner = new Scanner(System.in);


    static {
    }

    public Studentdao() {
        studentDtoList = new ArrayList<>();
        createIfNotExist();
        loadStudentsListFromFile();
    }
    private void createIfNotExist(){
        File file = new File(FILE_NAME);
        if (!file.exists()){
            try {
                if (file.createNewFile()){
                    System.out.println(SpecialColor.YELLOW + " Dosya Oluşturuldu "+SpecialColor.RESET);
                }
            }catch (IOException io){
                System.out.println(SpecialColor.RED + " Dosya olusturulurken hata olustu"+SpecialColor.RESET);
                io.printStackTrace();
            }
        }
    }

    private void saveToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME,true))) {
            for (StudentDto student:studentDtoList){
                bufferedWriter.write(studentToCsv(student) + "\n");
            }
            System.out.println(SpecialColor.YELLOW + "Öğrenci dosyaya kaydedildi" + SpecialColor.RESET);
        } catch (IOException io) {
            System.out.println(SpecialColor.RED + " Dosya Ekleme Hatası" + SpecialColor.RESET);
            io.printStackTrace();
        }
    }

    private void loadStudentsListFromFile() {
        // Listedeki verileri temizle
        studentDtoList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StudentDto student = csvToStudent(line);
                if (student != null) {
                    studentDtoList.add(student);
                }
            }
            //studentCounter = studentDtoList.size();
            // ✅ Öğrenciler içindeki en büyük ID'yi bul
            /*
            studentCounter = studentDtoList.stream()
                    .mapToInt(StudentDto::getId)
                    .max()
                    .orElse(0); // Eğer öğrenci yoksa sıfır başlat
            */


        } catch (IOException e) {
            System.out.println(SpecialColor.RED + "Dosya okuma hatası!" + SpecialColor.RESET);
            e.printStackTrace();
        }
    }
    private String studentToCsv(StudentDto student) {
        return
                        student.getId() + "," +          // Öğrenci ID'sini ekler
                        student.getName() + "," +        // Öğrenci adını ekler
                        student.getSurname() + "," +     // Öğrenci soyadını ekler
                        student.getMidTerm() + "," +     // Öğrenci vize notunu ekler
                                 student.getStatus() + ","+
                        student.getFinalTerm() + "," +   // Öğrenci final notunu ekler
                        student.getResultTerm() + "," +  // Öğrenci sonuç notunu ekler
                        student.getBirthDate() + "," +   // Öğrenci doğum tarihini ekler
                        student.geteStudentType();       // Öğrencinin eğitim türünü (Lisans, Yüksek Lisans vb.) ekler
    }
    private StudentDto csvToStudent(String csvLine) {
        try {
            String[] parts = csvLine.split(",");  // Satırı virgülle bölerek bir dizi oluşturur
            if (parts.length < 9) return null;    // **Eksik veri varsa işlemi durdurur ve null döndürür**

            // PersonDto =>  Integer id, String name, String surname, LocalDate birthDate
            // StudentDto =>  Integer id, String name, String surname, LocalDate birthDate, Double midTerm, Double finalTerm,EStudentType eStudentType
            StudentDto student = new StudentDto(
                    Integer.parseInt(parts[0]),  // ID değerini integer olarak dönüştürür
                    parts[1],                    // Adı alır
                    parts[2],// Soyadı alır
                    LocalDate.parse(parts[3]),// Doğum tarihini LocalDate formatına çevirir
                    Double.parseDouble(parts[4]), // Vize notunu double olarak dönüştürür
                    Double.parseDouble(parts[5]), // Final notunu double olarak dönüştürür
                    EStudentType.valueOf(parts[8]),
                            ERole.valueOf(parts[9]) // Öğrencinin eğitim türünü (Enum) çevirir
            );
            // **Geçti/Kaldı durumu CSV'den okunduğu gibi öğrenci nesnesine eklenir**
            student.setResultTerm(Double.parseDouble(parts[6])); // **Sonuç notunu ayarla**
            student.setStatus(parts[7]); // **Geçti/Kaldı durumunu CSV'den al**

            return student;
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "CSV'den öğrenci yükleme hatası!" + SpecialColor.RESET);
            return null; // Hata durumunda null döndürerek programın çökmesini engeller
        }
    }

    @Override
    @Deprecated //Eski metot yenisini kullanın
    public Optional<StudentDto> create(StudentDto studentDto) {
        try {
            // 📌 Verilerin doğrulanmasını sağlıyoruz
            validateStudent(studentDto);

            // Öğrenci Listesindeki En büyük ID Al
            maxId = studentDtoList
                    .stream()
                    .mapToInt(StudentDto::getId)
                    .max()
                    .orElse(0); // ;eğer öğrenci yoksa Sıfırdan başlat

            // Yeni Öğrenciyi ID'si En büyük olan ID'nin 1 fazlası
            studentDto.setId(maxId+1);

            // ID'yi artırıp nesneye atıyoruz
            // 📌 **ID artık public static olduğu için her sınıftan erişilebilir!**
            studentDtoList.add(studentDto);
            saveToFile();

            System.out.println(studentDto+ SpecialColor.GREEN + "✅ Öğrenci başarıyla eklendi!" + SpecialColor.RESET);
            return Optional.of(studentDto);

        } catch (IllegalArgumentException e) {
            System.out.println(SpecialColor.RED + "⛔ Hata: " + e.getMessage() + SpecialColor.RESET);
            return null; // Hata durumunda nesne oluşturulmaz
        }
    }
    public void validateStudent(StudentDto studentDto){
        if (studentDto.getName() == null || !studentDto.getName().matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")){
            throw  new IllegalArgumentException("Ad yalnızca harf içermeli");
        }
        if (studentDto.getSurname()== null || !studentDto.getSurname().matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")){
            throw  new IllegalArgumentException("SoyAd yalnızca harf içermeli");
        }
        if (studentDto.getMidTerm() == null ||studentDto.getMidTerm()<0||studentDto.getMidTerm()>100){
            throw new IllegalArgumentException("Vize notu 0 ile 100 arasında olmalıdır.");

        }
        if (studentDto.getFinalTerm() == null ||studentDto.getFinalTerm()<0||studentDto.getFinalTerm()>100){
            throw new IllegalArgumentException("Final notu 0 ile 100 arasında olmalıdır.");
        }
        if (studentDto.getBirthDate() == null || studentDto.getBirthDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Doğum tarihi bugünden büyük olamaz.");
        }
        if (studentDto.geteStudentType() == null){
            throw new IllegalArgumentException("Öğrenci türü boş olamaz.");
        }

    }
    @Override
    @SuppressWarnings("unchacked") // derleyici uyarılarını bastırmak için kullanırız
    public ArrayList<StudentDto> list() {
        if (studentDtoList.isEmpty()) {
            System.out.println(SpecialColor.RED + " Öğrenci Yoktur" + SpecialColor.RESET);
            throw new StudentNotFoundException("Öğrenci Yoktur");
        } else {
            System.out.println(SpecialColor.BLUE + "Öğrenci Listesi" + SpecialColor.RESET);
           // studentDtoList.forEach(System.out::println);
            for (StudentDto student:studentDtoList){
                Double result = student.getResultTerm() !=null ? student.getResultTerm() : 0.0;
                System.out.println("ID" +student.getId()+
                        " | Ad:"+student.getName()
                +" |Sonuç: "+student.getResultTerm()
                +" |Durum: "+student.getStatus());
            }
        }
        return studentDtoList;
    }
    public Optional<StudentDto> findByName(String name) {
        /*
        1.YOl
        Optional<StudentDto> student = studentDtoList.stream().filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
        return student.orElseThrow(() ->new StudentNotFoundException(name + "idli öğrenci bulunamadı"));
         */
        return studentDtoList
                .stream()
                .filter(s ->s.getName().equalsIgnoreCase(name))
                .findFirst();

    }
        @Override
    public Optional<StudentDto> findByid(Integer id) {
        /*
        //studentDtoList.stream().filter(temp ->temp.getName().equalsIgnoreCase(name))
                .forEach(System.out::println);
        boolean found = studentDtoList.stream().filter(temp -> temp.getId().equals(id))
                .peek(System.out::println)
                .findAny()//Data var mı yok mu
                .isPresent();
        if (!found) {
            throw new StudentNotFoundException(id + " id li öğrenci Bulunamadı");
        }
        return null;
         */
            /*
        Optional<StudentDto> student =studentDtoList.stream()
                .filter(s -> s.getId().equals(id)).findFirst();
        return student.orElseThrow(() ->new StudentNotFoundException(id + "idli öğrenci bulunamadı"));
             */
            return studentDtoList
                    .stream()
                    .filter(s -> s.getId().equals(id))
                    .findFirst();
    }
    @Override
    public Optional<StudentDto> update(int id, StudentDto studentDto) {
        try {
        for (StudentDto temp : studentDtoList) {
            if (temp.getId().equals(id)) {
                temp.setName(studentDto.getName());
                temp.setSurname(studentDto.getSurname());
                temp.setBirthDate(studentDto.getBirthDate());
                temp.setMidTerm(studentDto.getMidTerm());
                temp.setFinalTerm(studentDto.getFinalTerm());
                temp.setResultTerm(temp.getMidTerm()*0.4+temp.getFinalTerm()*0.6);
                temp.seteStudentType(studentDto.geteStudentType());
                //Güncellenmiş öğrenci bilgileri
                System.out.println(SpecialColor.BLUE + temp + "Öğrenci Bilgileri Güncellendi" + SpecialColor.RESET);
                //Dosayay kaydet
                saveToFile();
                return Optional.of(temp);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
            throw new StudentNotFoundException("Öğrenci Bulunamadı");
        }
        return Optional.empty();
    }
    @Override
    public Optional<StudentDto> delete(int id) {
        /*
        boolean removed = studentDtoList.removeIf(temp -> temp.getId() == id);
        if (removed) {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silindi " + SpecialColor.RESET);
            // File Ekle
            saveToFile();
            return Optional.empty();
        } else {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silinmedi " + SpecialColor.RESET);
            throw new StudentNotFoundException("Öğrenci Silinmedi, ID bulunamadı!!");
        }
         */
        Optional<StudentDto> studentToDelete = findByid(id);
        if (studentToDelete.isPresent()){
            studentDtoList.remove(studentToDelete.get());
            System.out.println(SpecialColor.BLUE + " Öğrenci Silindi " + SpecialColor.RESET);
            saveToFile();
            return studentToDelete;
        }
        else {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silinmedi " + SpecialColor.RESET);
            throw new StudentNotFoundException("Öğrenci Silinmedi, ID bulunamadı!!");
        }
    }

    private EStudentType studentTypeMethod(){
        System.out.println("Öğrenci türünü seçiniz.\n1-Lisans\n2-Yüksek Lisans\n3-Doktora");
        int typeChooice = scanner.nextInt();
        EStudentType swichCaseStudent = switch (typeChooice){
            case 1 -> EStudentType.UNDERGRADUATE;
            case 2 -> EStudentType.GRADUATE;
            case 3 -> EStudentType.PHD;
            default -> EStudentType.OTHER;
        };
        return swichCaseStudent;
    }
    public void chooiseSduentAdd(){
        while (true) { // Kullanıcı geçerli giriş yapana kadar döngü devam eder
            try {
                // 📌 Kullanıcıdan geçerli bir ad alana kadar döngüde kal
                String name;
                while (true) {
                    System.out.print("Öğrenci Adı: ");
                    name = scanner.nextLine().trim();
                    if (name.matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) break;
                    System.out.println(SpecialColor.RED + "⛔ Geçersiz ad! Sadece harf giriniz." + SpecialColor.RESET);
                }

                // 📌 Kullanıcıdan geçerli bir soyad alana kadar döngüde kal
                String surname;
                while (true) {
                    System.out.print("Öğrenci Soyadı: ");
                    surname = scanner.nextLine().trim();
                    if (surname.matches("^[a-zA-ZığüşöçİĞÜŞÖÇ]+$")) break;
                    System.out.println(SpecialColor.RED + "⛔ Geçersiz soyad! Sadece harf giriniz." + SpecialColor.RESET);
                }

                // 📌 Kullanıcıdan geçerli bir doğum tarihi alana kadar döngüde kal
                LocalDate birthDate;
                while (true) {
                    System.out.print("Doğum Tarihi (YYYY-MM-DD): ");
                    String input = scanner.nextLine().trim();
                    try {
                        birthDate = LocalDate.parse(input);
                        if (!birthDate.isAfter(LocalDate.now())) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz doğum tarihi! Gelecekte olamaz." + SpecialColor.RESET);
                    } catch (Exception e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz format! Lütfen YYYY-MM-DD olarak giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Kullanıcıdan geçerli bir vize notu alana kadar döngüde kal
                double midTerm;
                while (true) {
                    System.out.print("Vize Notu (0-100): ");
                    String input = scanner.nextLine().trim();
                    try {
                        midTerm = Double.parseDouble(input);
                        if (midTerm >= 0 && midTerm <= 100) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz vize notu! 0-100 arasında giriniz." + SpecialColor.RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz giriş! Lütfen bir sayı giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Kullanıcıdan geçerli bir final notu alana kadar döngüde kal
                double finalTerm;
                while (true) {
                    System.out.print("Final Notu (0-100): ");
                    String input = scanner.nextLine().trim();
                    try {
                        finalTerm = Double.parseDouble(input);
                        if (finalTerm >= 0 && finalTerm <= 100) break;
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz final notu! 0-100 arasında giriniz." + SpecialColor.RESET);
                    } catch (NumberFormatException e) {
                        System.out.println(SpecialColor.RED + "⛔ Geçersiz giriş! Lütfen bir sayı giriniz." + SpecialColor.RESET);
                    }
                }

                // 📌 Öğrenci türünü seçme
                EStudentType studentType = studentTypeMethod();

                // 📌 Öğrenci nesnesini oluştur
                // Integer id, String name, String surname, LocalDate birthDate,Double midTerm, Double finalTerm,EStudentType eStudentType
                StudentDto newStudent = new StudentDto(maxId, name, surname,birthDate, midTerm, finalTerm, studentType,ERole.STUDENT);
                Optional<StudentDto> createdStudent = create(newStudent);

                if (createdStudent != null) {
                    break; // 🔹 Başarıyla eklenirse döngüden çık
                } else {
                    System.out.println(SpecialColor.RED + "⛔ Öğrenci eklenirken hata oluştu. Lütfen tekrar deneyin." + SpecialColor.RESET);
                }
            } catch (Exception e) {
                System.out.println(SpecialColor.RED + "⛔ Beklenmeyen hata oluştu: " + e.getMessage() + SpecialColor.RESET);
                scanner.nextLine(); // 🔹 Hata sonrası buffer temizleme
            }
        }
    }

    public void chooiseSduentList(){
        try {
            list();
        }catch (StudentNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    public void chooiseStudentSearch(){
        list();
        System.out.println("Aranacak öğrenci ismi: ");
        String name = scanner.nextLine();
        try {
            System.out.println(findByName(name));
        }catch (StudentNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    public void chooiseStudentUpdate(){
        list();
        System.out.print("Güncellenecek Öğrenci ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Boşluğu temizleme

        System.out.print("Yeni Adı: ");
        String nameUpdate = scanner.nextLine();

        System.out.print("Yeni Soyadı: ");
        String surnameUpdate = scanner.nextLine();

        System.out.print("Doğum Tarihi (YYYY-MM-DD): ");
        LocalDate birthDateUpdate = LocalDate.parse(scanner.nextLine());

        System.out.print("Yeni Vize Notu: ");
        double midTermUpdate = scanner.nextDouble();

        System.out.print("Yeni Final Notu: ");
        double finalTermUpdate = scanner.nextDouble();

        //  // Integer id, String name, String surname, LocalDate birthDate,Double midTerm, Double finalTerm,EStudentType eStudentType
        StudentDto studentUpdate = new StudentDto(id, nameUpdate, surnameUpdate,birthDateUpdate, midTermUpdate, finalTermUpdate, studentTypeMethod(),ERole.STUDENT);
        try {
            update(id, studentUpdate);
            System.out.println("Öğrenci başarıyla güncellendi.");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void chooiseStudentDelete(){
        list();
        System.out.println("Silinecek Öğrenci Id si giriniz: ");
        int deleteId = scanner.nextInt();
        delete(deleteId);
    }
    public void chooiseStudentSum(){
        System.out.println("Toplam Öğrenci Sayısı: "+ studentDtoList.size());
    }
    public void chooiseRandomStudent(){
        if (!studentDtoList.isEmpty()){
            StudentDto randomStudent = studentDtoList.get((int) (Math.random() * studentDtoList.size()));
            System.out.println("Rastgele Seçilen öğrenci :" + randomStudent);
        }else {
            System.out.println("Sistemde Öğrenci yoktur");
        }

    }
    public void chooiseStudentNoteAvarege(){
        if (!studentDtoList.isEmpty()){
            double avg=studentDtoList.stream()
                    .mapToDouble(StudentDto::getResultTerm)
                    .average()
                    .orElse(0.0);
            System.out.println("Öğrenci Not ortalaması: " + avg);
            if (avg >= 55){
                System.out.println("Öğrenci geçti");
            }
            else {
                System.out.println("Öğrenci Geçmedi");
            }
        }else {
            System.out.println("Öğrenci Listesi Boş");
        }
    }
    public void chooiseStudentNoteMinAndMax(){
        if (!studentDtoList.isEmpty()) {
            StudentDto maxStudent = studentDtoList.stream()
                    .max((s1, s2) -> Double.compare(s1.getResultTerm(), s2.getResultTerm()))
                    .orElse(null);

            StudentDto minStudent = studentDtoList.stream()
                    .min((s1, s2) -> Double.compare(s1.getResultTerm(), s2.getResultTerm()))
                    .orElse(null);

            System.out.println("En Yüksek Not Alan Öğrenci: " + maxStudent);
            System.out.println("En Düşük Not Alan Öğrenci: " + minStudent);
        } else {
            System.out.println("Öğrenci listesi boş.");
        }
    }
    public void chooiseStudentBirthDaySortDate(){
        studentDtoList.stream()
                .sorted((s1, s2) -> s1.getBirthDate().compareTo(s2.getBirthDate()))
                .forEach(System.out::println);
    }
    public List<StudentDto> listStudentsWithStatus(){
        List<StudentDto> studentDtoStatus = list();
        return studentDtoStatus;
    }
    public void chooiseExit(){
        System.out.println("Sistemden Çıkılıyor... ");
        scanner.close();
        return;
    }
    @Override
    public void chooise() {
        Studentdao studentManeger = new Studentdao();

        while (true) {
            System.out.println("1.Öğrenci Ekle");
            System.out.println("2.Öğrenci Listele");
            System.out.println("3.Öğrenci Ara");
            System.out.println("4.Öğrenci Güncelle");
            System.out.println("5.Öğrenci Sil");
            System.out.println("6.Öğrenci Toplam Öğrenci Sayısı");
            System.out.println("7.Öğrenci Rastgele");
            System.out.println("8.Öğrenci Not Hesapla");
            System.out.println("9.Öğrenci En yüksek ve en düşük notları göster");
            System.out.println("10.Öğrenci sıralaması Doğum gününe göre göster");
            System.out.println("11.Öğrenci Geçti/Kaldı Durumunu Göster: ");
            System.out.println("12.Çıkış ");
            System.out.println("Lütfen seçiminizi yapınız:  ");
            int choose = scanner.nextInt();
            scanner.nextLine(); // Buffer'ı temizle

            switch (choose) {
                case 1:
                    chooiseSduentAdd();
                    break;
                case 2:
                    chooiseSduentList();
                    break;
                case 3:
                    chooiseStudentSearch();
                    break;
                case 4:
                    chooiseStudentUpdate();
                    break;
                case 5:
                    chooiseStudentDelete();
                    break;
                case 6:
                    chooiseStudentSum();
                    break;
                case 7:
                    chooiseRandomStudent();
                    break;
                case 8:
                    chooiseStudentNoteAvarege();
                    break;
                case 9:
                    chooiseStudentNoteMinAndMax();
                    break;
                case 10:
                    chooiseStudentBirthDaySortDate();
                case 11:
                    listStudentsWithStatus();
                case 12:
                    chooiseExit();
                    break;
                default:
                    System.out.println("Geçersiz seçim yaptınız lütfen tekrar deneyiniz");
                    break;
            }

        }
    }
}