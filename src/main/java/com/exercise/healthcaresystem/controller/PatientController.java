package com.exercise.healthcaresystem.controller;

import com.exercise.healthcaresystem.model.Appointment;
import com.exercise.healthcaresystem.model.AppointmentManager;
import com.exercise.healthcaresystem.model.Patient;
import com.exercise.healthcaresystem.repo.AppointmentRepo;
import com.exercise.healthcaresystem.repo.PatientRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class PatientController {

    private PatientRepo patientRepo;
    private AppointmentRepo appointmentRepo;

    @Autowired
    public PatientController(PatientRepo patientRepo, AppointmentRepo appointmentRepo) {
        this.patientRepo = patientRepo;
        this.appointmentRepo = appointmentRepo;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/medical-staff")
    public String staff() {
        return "staff-form";
    }


    @GetMapping("/patient-records")
    public String viewRecords(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-records";
    }

    @PostMapping("/display-patient-records")
    public String displayPatientRec(@ModelAttribute("patient") Patient patientModel) {

        Patient patient = patientRepo.findPatientByPatientName(patientModel.getPatientName());

        if (patient != null) {
            patientModel.setId(patient.getId());
            patientModel.setPatientName(patient.getPatientName());
            patientModel.setPatientEmail(patient.getPatientEmail());
            patientModel.setEmail(patient.getEmail());
            patientModel.setPatientType(patient.getPatientType());
            return "display-patient-records";
        } else {
            return "patient-records";
        }

    }

    @GetMapping("/appointment")
    public String appointment(Model model){
        model.addAttribute("appointmentManager", new AppointmentManager());
        return "appointment-scheduler";
    }

    @PostMapping("/createAppointment")
    public String createApp(@Valid @ModelAttribute("appointmentManager") AppointmentManager appointmentModel, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "appointment-scheduler";
        }else {
            Patient patient = new Patient();
            Appointment appointment = new Appointment();

            patient.setPatientName(appointmentModel.getPatient().getPatientName());
            patient.setPatientEmail(appointmentModel.getPatient().getPatientEmail());
            patient.setEmail(appointmentModel.getPatient().getEmail());
            patient.setPatientType(appointmentModel.getPatient().getPatientType());

            patientRepo.save(patient);

            appointment.setMedicalStaffId(appointmentModel.getAppointment().getMedicalStaffId());
            appointment.setPatientId(patient.getId());
            appointment.setDate(appointmentModel.getAppointment().getDate());

            appointmentRepo.save(appointment);

            return "appointment-conf";
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Patient> optionalPatient = patientRepo.findById(id);
        if(optionalPatient.isPresent()){
            Patient patient = optionalPatient.get();

            Patient patientModel = new Patient();
            patientModel.setId(patient.getId());
            patientModel.setEmail(patient.getEmail());
            patientModel.setPatientName(patient.getPatientName());
            patientModel.setPatientEmail(patient.getPatientEmail());
            patientModel.setPatientType(patient.getPatientType());

            model.addAttribute("patient", patientModel);
        }

        return "update-patient";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable Integer id, @ModelAttribute("patient") Patient patientModel){

            Optional<Patient>  optionalPatient = patientRepo.findById(id);

            if (optionalPatient.isPresent()){
                Patient updatedPatient = optionalPatient.get();

                updatedPatient.setPatientName(patientModel.getPatientName());
                updatedPatient.setPatientEmail(patientModel.getPatientEmail());
                updatedPatient.setEmail(patientModel.getEmail());
                updatedPatient.setPatientType(patientModel.getPatientType());

                patientRepo.save(updatedPatient);
            }else{
                return "unauthorized";
            }

            return "update-conf";
    }
}
