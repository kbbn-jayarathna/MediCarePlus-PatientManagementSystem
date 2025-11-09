package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppointmentStatusTrackerForm extends JFrame {
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel sidebarTopPanel;
    private JPanel menuContainer;
    private JPanel logoPanel;
    private JLabel logoIcon;
    private JButton btnDashboard;
    private JButton btnPatients;
    private JButton btnDoctors;
    private JButton btnAppointments;
    private JButton btnMonthlyReports;
    private JButton btnNotifications;
    private JPanel notificationSubmenuPanel;
    private JButton btnDoctorNotifications;
    private JButton btnPatientNotifications;
    private JPanel appointmentSubmenuPanel;
    private JButton btnNewSchedule;
    private JButton btnTrackStatus;
    private JButton btnDoctorAssignment;
    private JPanel contentPanel;
    private JPanel contentWrapper;
    private JPanel headerPanel;
    private JLabel lblTitle;
    private JLabel lblBreadcrumb;

    // Track notification,appointment expanded state
    private boolean isNotificationExpanded = false;
    private boolean isAppointmentExpanded = true;

    public AppointmentStatusTrackerForm() {
        setContentPane(mainPanel);
        setTitle("Enter your Title Here");
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        notificationSubmenuPanel.setVisible(false);

        setupMenuNavigation();
    }

    private void setupMenuNavigation() {
        btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDashboard();
            }
        });
        btnPatients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToPatients();
            }
        });
        btnDoctors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDoctors();
            }
        });
        btnAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleAppointments();
            }
        });
        btnMonthlyReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToMonthlyReports();
            }
        });
        btnNotifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleNotifications();
            }
        });
        btnDoctorNotifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDoctorNotifications();
            }
        });
        btnPatientNotifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToPatientNotifications();
            }
        });

        btnNewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToScheduling();
            }
        });
        btnTrackStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToStatusTracker();
            }
        });
        btnDoctorAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToAllocation();
            }
        });



    }

    private void toggleNotifications() {
        isNotificationExpanded = !isNotificationExpanded;
        if (!isNotificationExpanded) {
            btnNotifications.setText("\uD83D\uDD14  Notifications ▼");
        } else {
            btnNotifications.setText("\uD83D\uDD14  Notifications ▲");
        }
        notificationSubmenuPanel.setVisible(isNotificationExpanded);
        sidebarPanel.revalidate();
        sidebarPanel.repaint();
    }

    private void toggleAppointments() {
        isAppointmentExpanded = !isAppointmentExpanded;
        if (!isAppointmentExpanded) {
            btnAppointments.setText("\uD83D\uDCC5  Appointments ▼");
        } else {
            btnAppointments.setText("\uD83D\uDCC5  Appointments ▲");
        }
        appointmentSubmenuPanel.setVisible(isAppointmentExpanded);
        sidebarPanel.revalidate();
        sidebarPanel.repaint();
    }

    private void navigateToDashboard() {
        MainDashboardForm targetForm = new MainDashboardForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }


    private void navigateToPatients() {
        //System.out.println("Clicked: Patients");
        //JOptionPane.showMessageDialog(this, "Patients Page");

        PatientManagementForm targetForm = new PatientManagementForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();

    }

    private void navigateToDoctors() {
        DoctorManagementForm targetForm = new DoctorManagementForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }

    private void navigateToScheduling() {
        AppointmentSchedulingForm targetForm = new AppointmentSchedulingForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }

    private void navigateToStatusTracker() {
        AppointmentStatusTrackerForm targetForm = new AppointmentStatusTrackerForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }

    private void navigateToAllocation() {
        DoctorAllocationForm targetForm = new DoctorAllocationForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }


    private void navigateToMonthlyReports() {
        ReportGenerationForm form1 = new ReportGenerationForm();
        form1.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }

    private void navigateToDoctorNotifications() {
        DoctorNotificationConfigForm targetForm = new DoctorNotificationConfigForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }

    private void navigateToPatientNotifications() {
        PatientNotificationConfigForm targetForm = new PatientNotificationConfigForm();
        targetForm.setVisible(true);
        AppointmentStatusTrackerForm.this.dispose();
    }





}
