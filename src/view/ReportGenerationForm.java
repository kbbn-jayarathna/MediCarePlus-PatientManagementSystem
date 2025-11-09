package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;




public class ReportGenerationForm extends JFrame {
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
    private JPanel contentPanel;
    private JPanel contentWrapper;
    private JPanel headerPanel;
    private JLabel lblTitle;
    private JLabel lblBreadcrumb;
    private JPanel toolbarPanel;
    private JPanel toolbarLeftPanel;
    private JLabel lblMonth;
    private JComboBox comboMonth;
    private JComboBox comboYear;
    private JLabel lblYear;
    private JButton btnGenerate;
    private JPanel toolbarRightPanel;
    private JButton btnExportPDF;
    private JButton btnExportExcel;
    private JPanel summaryCardsPanel;
    private JPanel card1Panel;
    private JPanel card2Panel;
    private JPanel card3Panel;
    private JPanel card4Panel;
    private JPanel mainContentGrid;
    private JPanel doctorPerformancePanel;
    private JLabel lblDoctorPerformance;
    private JTable doctorTable;
    private JPanel chartsPanel;
    private JPanel chartPanel1;
    private JPanel chartPanel2;
    private JPanel chart1;
    private JPanel chart2;
    private JLabel card1Icon;
    private JLabel card1Title;
    private JLabel card1Value;
    private JLabel card1Change;
    private JButton btnNewSchedule;
    private JButton btnTrackStatus;
    private JButton btnDoctorAssignment;
    private JPanel appointmentSubmenuPanel;

    //private DashboardForm dashboardForm;

    // Track notification,appointment expanded state
    private boolean isNotificationExpanded = false;
    private boolean isAppointmentExpanded = false;

    public ReportGenerationForm() {
        //dashboardForm = new DashboardForm();

        setTitle("MediCare Plus - Monthly Reports Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);
        add(mainPanel);

        //change dropdown menu style
        comboMonth.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboMonth.setBackground(new Color(255, 255, 255));
        comboMonth.setForeground(new Color(21, 73, 94));
        comboYear.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboYear.setBackground(new Color(255, 255, 255));
        comboYear.setForeground(new Color(21, 73, 94));

        //insert data into the table
        String[] columns = {"ID", "Doctor Name", "Specialization","Total Appts","Completed","Utilization"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        doctorTable.setModel(model);
        Object[][] data = {
                {"D001", "Dr. Samantha Perera", "Cardiology", 40, 35, "87.5%"},
                {"D002", "Dr. Nimal Fernando", "Pediatrics", 30, 25, "83.3%"},
                {"D003", "Dr. Kavindi Jayasuriya", "Dermatology", 25, 20, "80.0%"},
                {"D004", "Dr. Roshan Silva", "Orthopedics", 28, 22, "78.6%"},
                {"D005", "Dr. Malsha Gunawardena", "General Medicine", 50, 45, "90.0%"}
        };

        for (Object[] row : data) {
            model.addRow(row);
        }
        //model.addRow(new Object[]{"D001", "Dr. Smith", "Cardiology",""});
        //model.addRow(new Object[]{"D002", "Dr. Brown", "Dermatology"});
        createAppointmentStatusChart();
        createSpecialityBreakdownChart();

        // Initially hide notification submenu
        notificationSubmenuPanel.setVisible(false);
        setupMenuNavigation();
        //setActiveButton(btnMonthlyReports);



        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);



    }
    // 🥧 Chart 1: Appointment Status Breakdown
    private void createAppointmentStatusChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Completed", 45);
        dataset.setValue("Scheduled", 25);
        dataset.setValue("Canceled", 15);
        dataset.setValue("Delayed", 10);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "",
                dataset,
                true, true, false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("Completed", new Color(0, 153, 76));
        plot.setSectionPaint("Scheduled", new Color(0, 102, 204));
        plot.setSectionPaint("Canceled", new Color(255, 80, 80));
        plot.setSectionPaint("Delayed", new Color(255, 204, 0));
        plot.setBackgroundPaint(Color.WHITE);
        pieChart.setBackgroundPaint(Color.WHITE);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chart1.setLayout(new BorderLayout());
        chart1.removeAll();
        chart1.add(chartPanel, BorderLayout.CENTER);
        chart1.validate();
    }

    // 📊 Chart 2: Speciality Breakdown
    private void createSpecialityBreakdownChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15, "No Appts", "Cardiology");
        dataset.addValue(10, "No Appts", "Pediatrics");
        dataset.addValue(8, "No Appts", "Dermatology");
        dataset.addValue(12, "No Appts", "Orthopedics");
        dataset.addValue(20, "No Appts", "General Medicine");

        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Speciality",
                "Number of Appoinments",
                dataset
        );

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 102, 204));

        ChartPanel chartPanel = new ChartPanel(barChart);
        chart2.setLayout(new BorderLayout());
        chart2.removeAll();
        chart2.add(chartPanel, BorderLayout.CENTER);
        chart2.validate();
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
        ReportGenerationForm.this.dispose();
    }


    private void navigateToPatients() {
        //System.out.println("Clicked: Patients");
        //JOptionPane.showMessageDialog(this, "Patients Page");

        PatientManagementForm targetForm = new PatientManagementForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();

    }

    private void navigateToDoctors() {
        DoctorManagementForm targetForm = new DoctorManagementForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();
    }

    private void navigateToScheduling() {
        AppointmentSchedulingForm targetForm = new AppointmentSchedulingForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();
    }

    private void navigateToStatusTracker() {
        AppointmentStatusTrackerForm targetForm = new AppointmentStatusTrackerForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();
    }

    private void navigateToAllocation() {
        DoctorAllocationForm targetForm = new DoctorAllocationForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();
    }


    private void navigateToMonthlyReports() {
        ReportGenerationForm form1 = new ReportGenerationForm();
        form1.setVisible(true);
        ReportGenerationForm.this.dispose();
    }

    private void navigateToDoctorNotifications() {
        DoctorNotificationConfigForm targetForm = new DoctorNotificationConfigForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();
    }

    private void navigateToPatientNotifications() {
        PatientNotificationConfigForm targetForm = new PatientNotificationConfigForm();
        targetForm.setVisible(true);
        ReportGenerationForm.this.dispose();
    }


    public static void main(String[] args) {

        new ReportGenerationForm();
    }

}


