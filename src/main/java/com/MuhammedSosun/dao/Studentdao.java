package com.MuhammedSosun.dao;

import com.MuhammedSosun.dto.StudentDto;
import com.MuhammedSosun.exception.StudentNotFoundException;
import com.MuhammedSosun.Utils.SpecialColor;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Studentdao implements IDaoGenerics<StudentDto>{
    private ArrayList<StudentDto> studentDtoList = new ArrayList<>();
    private int studentCounter = 0;
    private static final String FILE_NAME = "student.txt";

    Scanner scanner = new Scanner(System.in);


    static {
    }

    public Studentdao() {
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
            studentCounter = studentDtoList.size();
            System.out.println(SpecialColor.BLUE + "Dosyadan yüklenen öğrenci sayısı"+studentCounter + SpecialColor.RESET);

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
            if (parts.length < 8) return null;    // **Eksik veri varsa işlemi durdurur ve null döndürür**

            // PersonDto =>  Integer id, String name, String surname, LocalDate birthDate
            // StudentDto =>  Integer id, String name, String surname, LocalDate birthDate, Double midTerm, Double finalTerm,EStudentType eStudentType
            return new StudentDto(
                    Integer.parseInt(parts[0]),  // ID değerini integer olarak dönüştürür
                    parts[1],                    // Adı alır
                    parts[2],                    // Soyadı alır// Doğum tarihini LocalDate formatına çevirir
                    Double.parseDouble(parts[3]), // Vize notunu double olarak dönüştürür
                    Double.parseDouble(parts[4]), // Final notunu double olarak dönüştürür
                    LocalDate.parse(parts[6]),
                    EStudentType.valueOf(parts[8]) // Öğrencinin eğitim türünü (Enum) çevirir
            );
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "CSV'den öğrenci yükleme hatası!" + SpecialColor.RESET);
            return null; // Hata durumunda null döndürerek programın çökmesini engeller
        }
    }

    @Override
    public StudentDto create(StudentDto studentDto) {
        try {
            // 📌 Verilerin doğrulanmasını sağlıyoruz
            validateStudent(studentDto);



            // Yeni Öğrenciyi ID'si En büyük olan ID'nin 1 fazlası
            studentDto.setId(++studentCounter);

            // ID'yi artırıp nesneye atıyoruz
            // 📌 **ID artık public static olduğu için her sınıftan erişilebilir!**
            studentDtoList.add(studentDto);
            saveToFile();

            System.out.println(studentDto+ SpecialColor.GREEN + "✅ Öğrenci başarıyla eklendi!" + SpecialColor.RESET);
            return studentDto;

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
            throw  new IllegalArgumentException("Ad yalnızca harf içermeli");
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
    public ArrayList<StudentDto> list() {
        if (studentDtoList.isEmpty()) {
            System.out.println(SpecialColor.RED + " Öğrenci Yoktur" + SpecialColor.RESET);
            throw new StudentNotFoundException("Öğrenci Yoktur");
        } else {
            System.out.println(SpecialColor.BLUE + "Öğrenci Listesi" + SpecialColor.RESET);
            studentDtoList.forEach(System.out::println);
        }
        return studentDtoList;
    }
    public StudentDto findByName(String name) {
        Optional<StudentDto> student = studentDtoList.stream().filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
        return student.orElseThrow(() ->new StudentNotFoundException(name + "idli öğrenci bulunamadı"));

    }
        @Override
    public StudentDto findByid(Integer id) {
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
        Optional<StudentDto> student =studentDtoList.stream()
                .filter(s -> s.getId().equals(id)).findFirst();
        return student.orElseThrow(() ->new StudentNotFoundException(id + "idli öğrenci bulunamadı"));

    }



    @Override
    public StudentDto update(int id, StudentDto studentDto) {
        for (StudentDto temp : studentDtoList) {
            if (temp.getId() == id) {
                temp.setName(studentDto.getName());
                temp.setSurname(studentDto.getSurname());
                temp.setBirthDate(studentDto.getBirthDate());
                temp.setMidTerm(studentDto.getMidTerm());
                temp.setFinalTerm(studentDto.getFinalTerm());
                temp.seteStudentType(studentDto.geteStudentType());
                //Güncellenmiş öğrenci bilgileri
                System.out.println(SpecialColor.BLUE + temp + "Öğrenci Bilgileri Güncellendi" + SpecialColor.RESET);
                //Dosayay kaydet
                saveToFile();
            }
        }
        System.out.println(SpecialColor.RED + " Öğrenci Bulunamadı " + SpecialColor.RESET);
        return studentDto;
    }
    @Override
    public StudentDto delete(int id) {
        boolean removed = studentDtoList.removeIf(temp -> temp.getId() == id);
        if (removed) {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silindi " + SpecialColor.RESET);
            // File Ekle
            saveToFile();
        } else {
            System.out.println(SpecialColor.BLUE + " Öğrenci Silinmedi " + SpecialColor.RESET);
        }
        return null;
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
        System.out.println("Öğrenci Adı");
        String name = scanner.nextLine();
        System.out.println("Öğrenci Soyadı");
        String surname = scanner.nextLine();
        System.out.println("Öğrenci Doğum Tarihi YYYY-MM-DD");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Vize Puanı");
        double midTerm = scanner.nextDouble();
        System.out.println("Final Puanı");
        double finalTerm = scanner.nextDouble();
        EStudentType eStudentType = studentTypeMethod();
        StudentDto newStudent = new StudentDto(++studentCounter, name, surname, midTerm, finalTerm, birthDate,eStudentType);
        create(newStudent);
        System.out.println("Öğrenci Başarıyla Eklendi");
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
        System.out.println("Güncellenecek Öğrenci id si giriniz: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Yeni Öğrenci Adı");
        String nameUpdate = scanner.nextLine();
        System.out.println("Yeni Öğrenci Soyadı");
        String surnameUpdate = scanner.nextLine();
        System.out.println("Öğrenci Doğum Tarihi YYYY-MM-DD");
        LocalDate birthDateUpdate = LocalDate.parse(scanner.nextLine());
        System.out.println("Vize Puanı");
        double midTermUpdate = scanner.nextDouble();
        System.out.println("Final Puanı");
        double finalTermUpdate = scanner.nextDouble();

        StudentDto studentDtoUpdate = StudentDto.builder().
                name(nameUpdate)
                .surname(surnameUpdate)
                .midTerm(midTermUpdate)
                .finalTerm(finalTermUpdate)
                .birthDate(birthDateUpdate).
                eStudentType(studentTypeMethod()).
                build();
        try {
            update(id, studentDtoUpdate);
        } catch (StudentNotFoundException e) {
            System.out.println(SpecialColor.RED + e.getMessage());
            e.printStackTrace();
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
            System.out.println("11.Çıkış ");
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
                    chooiseExit();
                    break;
                default:
                    System.out.println("Geçersiz seçim yaptınız lütfen tekrar deneyiniz");
                    break;
            }

        }
    }
}