package com.MuhammedSosun.dao;

import com.MuhammedSosun.Utils.SpecialColor;
import com.MuhammedSosun.dto.TeacherDto;
import com.MuhammedSosun.exception.TeacherNotFoundException;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TeacherDao implements IDaoGenerics<TeacherDto> {
    private final List<TeacherDto> teacherDtoList;
    private final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final String FILE_NAME = "teacher.txt";

    private final FileHandler fileHandler = new FileHandler();

    public TeacherDao() {
        teacherDtoList = new ArrayList<>();
        fileHandler.createIfNotExist();
        fileHandler.loadStudentsListFromFile();
    }

    static {
        System.out.println(SpecialColor.RED + " Static: TeacherDao" + SpecialColor.RESET);
    }
    private class FileHandler{
        private void createIfNotExist() {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println(SpecialColor.YELLOW + " Dosya Oluşturuldu " + SpecialColor.RESET);
                    }
                } catch (IOException io) {
                    System.out.println(SpecialColor.RED + " Dosya olusturulurken hata olustu" + SpecialColor.RESET);
                    io.printStackTrace();
                }
            }
        }

        private void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                for (TeacherDto teacher : teacherDtoList) {
                    writer.write(teacherTocsv(teacher) + "\n");
                }
                System.out.println(SpecialColor.YELLOW + "Öğretmen dosyaya kaydedildi" + SpecialColor.RESET);
            } catch (IOException io) {
                System.out.println(SpecialColor.RED + " Dosya Ekleme Hatası" + SpecialColor.RESET);
                io.printStackTrace();
            }
        }

        private void loadStudentsListFromFile() {
            try (BufferedReader read = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = read.readLine()) != null) {
                    TeacherDto teacher = csvToTeacher(line);
                    if (teacher != null) {
                        teacherDtoList.add(teacher);
                    }
                }
            } catch (IOException io) {
                System.out.println(SpecialColor.RED + "Dosya okuma hatası!" + SpecialColor.RESET);
                io.printStackTrace();
            }
        }
    }



    private String teacherTocsv(TeacherDto teacher) {
        return teacher.id() + " " + teacher.name() + " " + teacher.surname() + " " + teacher.birthDate() + " " +
                teacher.eTeacherSubject() + "," +
                teacher.yearsOfExperience() + "," +
                teacher.isTenured() + "," +
                teacher.salary();
    }

    private TeacherDto csvToTeacher(String csvLine) {
        try {
            String[] parts = csvLine.split(",");
            if (parts.length != 8) {
                System.out.println("Hatalı CSV yapısı: " + csvLine);
                return null;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate birthDate = null;
            try {
                if (!parts[3].isBlank()) {
                    birthDate = LocalDate.parse(parts[3], formatter);
                }
            } catch (DateTimeException e) {
                System.err.println("Geçersiz tarih formatı: " + parts[3] + " (Beklenen format: yyyy-MM-dd)");
                return null;
            }
            return new TeacherDto(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    birthDate,
                    ETeacherSubject.valueOf(parts[4]),
                    Integer.parseInt(parts[5]),
                    Boolean.parseBoolean(parts[6]),
                    Double.parseDouble(parts[7])
            );

        } catch (Exception e) {
            System.err.println("CSV ayrıştırma hatası: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<TeacherDto> create(TeacherDto teacher) {
        teacherDtoList.add(teacher);
        fileHandler.saveToFile();
        return Optional.of(teacher);
    }

    @Override
    public Optional<TeacherDto> findByid(Integer id) {
        /*
        if (teacherDtoList.isEmpty()) throw new TeacherNotFoundException("Öğretmen Bulunamadı");

        return teacherDtoList.stream().filter(temp -> temp.id() == id).findFirst().
                orElseThrow(() -> new TeacherNotFoundException("İd li Öğretmen Yoktur"));
         */
        return teacherDtoList.stream()
                .filter(temp -> temp.id().equals(id))
                .findFirst()
                .or(() -> Optional.empty());
    }

    @Override
    public Optional<TeacherDto> findByName(String name) {
        /*
        if (teacherDtoList.isEmpty()) throw new TeacherNotFoundException(" İsimli Öğretmen Bulunamadı");
        return teacherDtoList.stream().filter(temp -> temp.name().equalsIgnoreCase(name)).findFirst().
                orElseThrow(() -> new TeacherNotFoundException("İsimli Öretmen bulunmaadı"));
         */
        return teacherDtoList
                .stream()
                .filter(s-> s.name().equalsIgnoreCase(name))
                .findFirst()
                .or(() -> Optional.empty());
    }

    @Override
    public List<TeacherDto> list() {
        return new ArrayList<>(teacherDtoList);
    }

    @Override
    public Optional<TeacherDto> update(int id, TeacherDto updatedTeacher) {
        for (int i = 0; i < teacherDtoList.size(); i++) {
            if (teacherDtoList.get(i).id() == id) {
                teacherDtoList.set(i, updatedTeacher);
                fileHandler.saveToFile();
                return Optional.of(updatedTeacher);
            }

        }
        throw new TeacherNotFoundException("Güncellenecek Öğretmen Bulunamadı");
    }

    @Override
    public Optional<TeacherDto> delete(int id) {
        Optional<TeacherDto> teacher = teacherDtoList.stream()
                .filter(t -> t.id() == id).findFirst();
        teacher.ifPresentOrElse(teacherDtoList::remove,() -> {throw new TeacherNotFoundException(id + "ID 'li Öğretmen bulunamadı");});
        fileHandler.saveToFile();
        return teacher;
    }
    public ETeacherSubject teacherTypeMethod(){
        System.out.println("Öğretmen türünü seçiniz.\n1-HISOTRY\n2-BIOLOGY\n3-CHEMISTRY\n4-COMPUTER_SCIENCE\n5-MATHEMATICS");
        int thypeChooise =scanner.nextInt();
        ETeacherSubject swichCaseTeacher = switch (thypeChooise){
            case 1-> ETeacherSubject.HISTORY;
            case 2-> ETeacherSubject.BIOLOGY;
            case 3-> ETeacherSubject.CHEMISTRY;
            case 4-> ETeacherSubject.COMPUTER_SCIENCE;
            case 5-> ETeacherSubject.MATHEMATICS;
            default -> ETeacherSubject.OTHER;

        };
        return swichCaseTeacher;
    }

    @Override
    public void chooise() {
        while (true) {
            try {
                System.out.println("\n===== ÖĞRETMEN YÖNETİM SİSTEMİ =====");
                System.out.println("1. Öğretmen Ekle");
                System.out.println("2. Öğretmen Listele");
                System.out.println("3. Öğretmen Ara");
                System.out.println("4. Öğretmen Güncelle");
                System.out.println("5. Öğretmen Sil");
                System.out.println("6. Rastgele Öğretmen Seç");
                System.out.println("7. Öğretmenleri Yaşa Göre Sırala");
                System.out.println("8. Çıkış");
                System.out.print("\nSeçiminizi yapınız: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Boşluğu temizleme
                switch (choice) {
                    case 1 -> addTeacher();
                    case 2 -> teacherDtoList();
                    case 3 -> searchTeacher();
                    case 4 -> updateTeacher();
                    case 5 -> deleteTeacher();
                    case 6 -> chooseRandomTeacher();
                    case 7 -> sortTeachersByAge();
                    case 8 -> {
                        System.out.println("Sistemden Çıkış Yapılıyor...");
                        return;
                    }
                    default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                }
            } catch (Exception e) {
                System.out.println("⛔ Beklenmeyen bir hata oluştu: " + e.getMessage());
                scanner.nextLine(); // Scanner'ı temizle
            }
        }
    }

    private void addTeacher(){
        System.out.println("Öğretmen ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Öğretmen İsim: ");
        String name = scanner.nextLine();
        System.out.println("Öğretmen Soyisim: ");
        String surname = scanner.nextLine();
        System.out.println("Öğretmen Doğum Tarihi: ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Uzmanlık alanı");
        ETeacherSubject eTeacherSubject = teacherTypeMethod();
      //  String subject = scanner.nextLine();
        System.out.print("Deneyim Yılı: ");
        int yearsOfExperience = scanner.nextInt();

        System.out.print("Kadrolu mu? (true/false): ");
        boolean isTenured = scanner.nextBoolean();

        System.out.print("Maaş: ");
        double salary = scanner.nextDouble();

        TeacherDto teacher = new TeacherDto(id,name,surname,birthDate,eTeacherSubject,yearsOfExperience,isTenured,salary);
        teacherDtoList.add(teacher);
        System.out.println("Öğretmen başarıyla eklendi.");
    }
    private void teacherDtoList(){
        if (teacherDtoList.isEmpty()) {
            System.out.println("Sistemde Kayıtlı öğretmen yok");
            return;
        }
        System.out.println("\n=== Öğretmen Listesi ===");
        teacherDtoList.forEach(t -> System.out.println(t.fullName() + "-" + t.eTeacherSubject()));
    }
    private void searchTeacher(){
        System.out.print("Aranacak öğretmenin adı: ");
        String name = scanner.nextLine();


        try {
            TeacherDto teacher =  findByName(name).orElseThrow(() -> new TeacherNotFoundException("Öğretmen Bulunamadı" + name));
            System.out.println("Bulunan Öğretmen: " + teacher.fullName() + " - " + teacher.eTeacherSubject());
        }catch (TeacherNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    private void updateTeacher() {
        System.out.print("Güncellenecek öğretmenin ID'si: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            TeacherDto existingTeacher = findByid(id).orElseThrow(() ->new TeacherNotFoundException("ID Bulunamadı" + id));

            System.out.print("Yeni Adı (Mevcut: " + existingTeacher.name() + "): ");
            String name = scanner.nextLine();
            System.out.print("Yeni Soyadı (Mevcut: " + existingTeacher.surname() + "): ");
            String surname = scanner.nextLine();
            System.out.print("Yeni Maaş (Mevcut: " + existingTeacher.salary() + "): ");
            double salary = scanner.nextDouble();

            TeacherDto updatedTeacher = new TeacherDto(
                    existingTeacher.id(),
                    name.isBlank() ? existingTeacher.name() : name,
                    surname.isBlank() ? existingTeacher.surname() : surname,
                    existingTeacher.birthDate(),
                    existingTeacher.eTeacherSubject(),
                    existingTeacher.yearsOfExperience(),
                    existingTeacher.isTenured(),
                    salary > 0 ? salary : existingTeacher.salary()
            );

            update(id, updatedTeacher);
            System.out.println("Öğretmen başarıyla güncellendi.");
        } catch (TeacherNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void deleteTeacher() {
        System.out.print("Silinecek öğretmenin ID'si: ");
        int id = scanner.nextInt();
        try {
            delete(id);
            System.out.println("Öğretmen başarıyla silindi.");
        } catch (TeacherNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void chooseRandomTeacher() {
        if (teacherDtoList.isEmpty()) {
            System.out.println("Kayıtlı öğretmen yok.");
            return;
        }
        TeacherDto teacher = teacherDtoList.get(random.nextInt(teacherDtoList.size()));
        System.out.println("Seçilen Rastgele Öğretmen: " + teacher.fullName());
    }

    private void sortTeachersByAge() {
        teacherDtoList.sort(Comparator.comparing(TeacherDto::birthDate));
        System.out.println("Öğretmenler yaşa göre sıralandı.");
        teacherDtoList();
    }
}