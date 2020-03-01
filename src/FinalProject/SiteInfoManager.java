package FinalProject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SiteInfoManager extends JFrame {

	// Login창
	LoginDialog ld;
	// 속성
	// 0. 메뉴바의 속성
	JMenuBar mb;

	// 화면구성함수의 속성
	// 1. 입력/수정 항목
	// 1-1. [기본 정보] 항목
	JTextField tf_siteName; // 사이트명
	JTextField tf_url; // 주소
	JTextField tf_id; // 아이디
	JTextField tf_passwd; // 비번

	// 1-2. [추가 정보] 항목
	JComboBox<SiteCategory> list_categ; // 분류
	JComboBox<String> list_prefer; // 선호도
	JTextArea ta_memo;

	// 1-3. 버튼 항목
	JButton reWrite;
	JButton Input;

	// 2.사이트 목록 탭
	// 2-1. 검색/정렬 항목 (p1영역)
	JComboBox<SiteCategory> list_search; // 검색 - 콤보박스
	JTextField searchField; // 검색 - 필터

	JComboBox<SiteCategory> sort_categ; // 정렬 - 분류
	JComboBox<String> sort_name; // 정렬 - 사이트이름
	JButton sortButton; // 정렬 - [정렬]버튼
	JButton defaultButton; // 정렬 - [기본]버튼

	// 2-2. 표 항목 (p2영역)
	JTable mTable; // 표

	// 2-3. 계정 정보보기 항목 (p3영역)
	JCheckBox cb; // 계정정보보기 체크박스
	JTextField id; // 아이디
	JTextField pass; // 비번
	JButton deleteButton; // [삭제] 버튼

	// 3 입력 버튼 클릭시 필요
	static SiteInfoList siteInfoList;

	// +사이트개수
	int siteSize;
	JLabel sizeLabel;
	JPanel regiPanel;

	// 표에서 선택한 행에 대한 SiteInfo 항목
	SiteInfo siteInfo;

	// CategoryManageDialog용 카테고리 리스트
	static SiteCategoryList list = new SiteCategoryList();
	static Vector<SiteCategory> v = new Vector<SiteCategory>();
	static Vector<SiteCategory> v2 = new Vector<SiteCategory>();
	SiteCategory normal_category = new SiteCategory("일반");
	SiteCategory total_category = new SiteCategory("전체");

	// 등록현황 탭 그래프
	GraphCanvas can;

	// 자동로그인
	JCheckBoxMenuItem sItem1;

	public SiteInfoManager() {
		super("민지와 서연이가 만든 인터넷 계정 관리 프로그램이지요");

		siteInfoList = new SiteInfoList();

		boolean boolCate = readCategory();
		if (boolCate == false) {
			list.addList(normal_category); // 일반
			list.addList(new SiteCategory("학교"));
			list.addList(new SiteCategory("포탈"));
			list.addList(new SiteCategory("정보"));
		}

		Iterator it = list.getList().iterator();
		while (it.hasNext()) {
			SiteCategory sc = (SiteCategory) it.next();
			v.add(sc);
			v2.add(sc);
		}
		v2.add(total_category);

		createMenu(); // 메뉴바 구성 함수
		buildGUI(); // 화면 구성 함수

		setSize(850, 650);
		setLocation(50, 100);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);

		boolean au = autoLoginCheck();
		ld = new LoginDialog(this);
		if (au == false) {
			ld.setVisible(true);
		} else if (au == true) {
			sItem1.setSelected(true);
		}

		// 로그인 성공 시 파일 읽어오는 함수
		readData();
		sizeLabel.setText(siteSize + "개의 사이트가 등록되었습니다.");

	}

	private boolean readCategory() {

		File file = new File("C:/JavaFinal/Category.txt");
		if (file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				try {
					while ((sLine = inFile.readLine()) != null) {
						String[] array = sLine.split("/");
						for (int i = 0; i < array.length; i++) {
							list.addList(new SiteCategory(array[i]));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean autoLoginCheck() {
		File file = new File("C:/JavaFinal/autoLogin.txt");
		if (file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				try {
					sLine = inFile.readLine();
					if (sLine.equals("true"))
						return true;
					else if (sLine.equals("false"))
						return false;
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	private void readData() {
		File file = new File("C:/JavaFinal/TableInfo.txt");
		if (file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				try {
					while ((sLine = inFile.readLine()) != null) {
						String[] array = sLine.split("#");
						SiteInfo s = new SiteInfo(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
						siteInfoList.addList(s);
						++siteSize;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		mTable.setModel(new InfoTableModel(siteInfoList));
	}

	// 메뉴바 구성 함수
	private void createMenu() {
		mb = new JMenuBar();
		setJMenuBar(mb); // 메뉴바를 프레임에 부착

		// JMenu
		JMenu fileMenu = new JMenu("파일(F)");
		JMenu manaMenu = new JMenu("관리(M)");
		JMenu setMenu = new JMenu("설정(S)");
		fileMenu.setMnemonic('F');
		manaMenu.setMnemonic('M');
		setMenu.setMnemonic('S');

		// JMenuItem
		JMenuItem fItem1 = new JMenuItem("엑셀 파일에서 가져오기(I)");
		JMenuItem fItem2 = new JMenuItem("엑셀 파일로 내보내기(E)");
		JMenuItem fItem3 = new JMenuItem("저장(S)");
		JMenuItem fItem4 = new JMenuItem("로그아웃(O)");
		JMenuItem fItem5 = new JMenuItem("종료(X)");
		fItem1.setMnemonic('I');
		fItem2.setMnemonic('E');
		fItem3.setMnemonic('S');
		fItem4.setMnemonic('O');
		fItem5.setMnemonic('X');
		// 비활성화
		fItem1.setEnabled(false);
		fItem2.setEnabled(false);

		JMenuItem mItem1 = new JMenuItem("사용자(U)");
		JMenuItem mItem2 = new JMenuItem("사이트 분류(C)");
		mItem1.setMnemonic('U');
		mItem2.setMnemonic('C');
		// 비활성화
		mItem1.setEnabled(false);

		sItem1 = new JCheckBoxMenuItem("자동 로그인(L)");
		JCheckBoxMenuItem sItem2 = new JCheckBoxMenuItem("계정정보 보기 상태 기억하기(V)");

		// JMenuItem을 JMenu에 부착
		fileMenu.add(fItem1);
		fileMenu.add(fItem2);
		fileMenu.add(fItem3);
		fileMenu.addSeparator();
		fileMenu.add(fItem4);
		fileMenu.add(fItem5);
		manaMenu.add(mItem1);
		manaMenu.add(mItem2);
		setMenu.add(sItem1);
		setMenu.add(sItem2);

		// 관리 - c 에 CategoryManageDialog에 대한 리스너 추가
		mItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryManageDialog dlg = new CategoryManageDialog(SiteInfoManager.this);
				dlg.setVisible(true);

			}

		});

		// 저장
		fItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					OutputStream output = new FileOutputStream("C:/JavaFinal/TableInfo.txt");
					Iterator it = siteInfoList.getList().iterator();
					String infoString = "";
					while (it.hasNext()) {
						SiteInfo info = (SiteInfo) it.next();
						infoString += info.getSiteName() + "#";
						infoString += info.getUrl() + "#";
						infoString += info.getId() + "#";
						infoString += info.getPasswd() + "#";
						infoString += info.getCateg() + "#";
						infoString += info.getPrefer() + "#";
						infoString += info.getMemo() + "#";
						infoString += " ";
						infoString += "\n";
					}
					byte[] by = infoString.getBytes();
					try {
						output.write(by);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					OutputStream output2 = new FileOutputStream("C:/JavaFinal/Category.txt");
					Iterator it = v.iterator();
					String outCategory = "";
					while (it.hasNext()) {
						outCategory += it.next() + "/";
					}
					byte[] by = outCategory.getBytes();
					try {
						output2.write(by);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}

		});
		// 파일 - 로그아웃
		fItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(SiteInfoManager.this, "로그아웃하시겠습니까?", "로그아웃",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					if (sItem1.isSelected()) { // 자동로그인 설정
						return;
					} else { // 자동로그인 설정 x
						LoginDialog.f.setText("");
						ld.setVisible(true);
					}

				} else
					return;

			}

		});

		// 파일 - 종료
		fItem5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(SiteInfoManager.this, "종료하시겠습니까?", "종료",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {

					System.exit(0);
				} else
					return;
			}

		});

		// 자동로그인 체크 여부
		sItem1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String autoString;
				OutputStream output = null;
				try {
					output = new FileOutputStream("C:/JavaFinal/AutoLogin.txt");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				if (e.getStateChange() == ItemEvent.SELECTED)
					autoString = "true";
				else
					autoString = "false";

				byte[] by = autoString.getBytes();
				try {
					output.write(by);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});

		// 계정정보보기 메뉴 버튼
		sItem2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					cb.setSelected(true);
				} else {
					cb.setSelected(false);
				}

			}

		});
		// JMenu를 JMenuBar에 부착
		mb.add(fileMenu);
		mb.add(manaMenu);
		mb.add(setMenu);
	}

	// 화면 구성 함수
	private void buildGUI() {
		JPanel p = new JPanel(new BorderLayout());
		p.add(InputAndModi(), BorderLayout.WEST);
		p.add(Tab(), BorderLayout.CENTER);
		p.add(RegiStatus(), BorderLayout.SOUTH);
		this.add(p);
	}

	// [입력/수정] 입력 패널 구성 함수 : BorderLayout.WEST
	private JPanel InputAndModi() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		p.setBorder(BorderFactory.createTitledBorder("입력/수정"));
//      p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); // 수직 정렬 **

		// [기본 정보] 항목
		JPanel normalInfo = new JPanel(new GridLayout(4, 1));
		normalInfo.setBorder(BorderFactory.createTitledBorder("기본 정보"));

		JPanel np1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel np2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel np3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel np4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		normalInfo.add(np1);
		normalInfo.add(np2);
		normalInfo.add(np3);
		normalInfo.add(np4);

		JLabel siteName = new JLabel("사이트명");
		tf_siteName = new JTextField(18);
		JLabel url = new JLabel("주소(URL)  https://");
		tf_url = new JTextField(12);
		JLabel id = new JLabel("아 이 디");
		tf_id = new JTextField(10);
		JLabel passwd = new JLabel("비밀번호");
		tf_passwd = new JTextField(10);
		np1.add(siteName);
		np1.add(tf_siteName);
		np2.add(url);
		np2.add(tf_url);
		np3.add(id);
		np3.add(tf_id);
		np4.add(passwd);
		np4.add(tf_passwd);

		// 이벤트리스너
		tf_siteName.addKeyListener(handler);
		tf_url.addKeyListener(handler);
		tf_id.addKeyListener(handler);
		tf_passwd.addKeyListener(handler);

		// [추가 정보] 항목
		JPanel addInfo = new JPanel();
		addInfo.setBorder(BorderFactory.createTitledBorder("추가 정보"));
		addInfo.setLayout(new BoxLayout(addInfo, BoxLayout.Y_AXIS)); // 수직 정렬 *

		JPanel ap1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel ap2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel ap3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addInfo.add(ap1);
		addInfo.add(ap2);
		addInfo.add(ap3);

		JLabel categ = new JLabel("분    류");
		list_categ = new JComboBox<SiteCategory>(v);
		// list_categ.setSelectedIndex(0); // 기본 선택된 리스트 : "일반"
		// list_categ.setSelectedItem(normal_category);
		JLabel prefer = new JLabel("선호도");
		String[] listData2 = { "미지정", "★★★★★", "☆★★★★", "☆☆★★★", "☆☆☆★★", "☆☆☆☆★", "☆☆☆☆☆" };
		list_prefer = new JComboBox<String>(listData2);
		list_prefer.setSelectedIndex(0); // 기본 선택된 리스트 : "미지정"
		JLabel memo = new JLabel("메   모 ");
		ta_memo = new JTextArea("", 4, 20);
		ap1.add(categ);
		ap1.add(list_categ);
		ap2.add(prefer);
		ap2.add(list_prefer);
		ap3.add(memo);
		ap3.add(new JScrollPane(ta_memo));

		// [새로작성], [입력] 버튼 // FlowLayout.RIGHT하면 오류뜸..
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		reWrite = new JButton("새로작성(N)");
		Input = new JButton("입력(I)");
		reWrite.setMnemonic('N');
		Input.setMnemonic('I');
		buttonPanel.add(reWrite);
		buttonPanel.add(Input);
		// 초기설정 - 비활성화
		reWrite.setEnabled(false);
		Input.setEnabled(false);

		p.add(normalInfo);
		p.add(addInfo);
		p.add(buttonPanel);

		registerButtonListener();

		return p;

	}

	// keyAdapter
	private KeyAdapter handler = new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent e) {
			if (tf_siteName.getText().length() > 0) {
				reWrite.setEnabled(true); // 하나라도 입력되면 활성화
				if (tf_url.getText().length() > 0) {
					// **
					// if, tf_id가 중복이라면
					for (int i = 0; i < mTable.getRowCount(); i++) {
						if (tf_url.getText().equals(mTable.getValueAt(i, 3))) {
							// 경고창 띄우기
							JOptionPane.showMessageDialog(SiteInfoManager.this, "이미 존재하는 url입니다! 다른 url을 입력해주세요 :)",
									"경고", JOptionPane.WARNING_MESSAGE);
						}
					}
					if (tf_id.getText().length() > 0) {
						if (tf_passwd.getText().length() > 0) {

							// Input 버튼 활성화
							Input.setEnabled(true); // 네 항목 모두 입력되면 활성화
						} else if (Input.getText().equals("수정(E)")) {
							Input.setEnabled(true);
						}
					}
				}
			}
		}

	};

	private void registerButtonListener() {
		Input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) (e.getSource());

				SiteInfo s = new SiteInfo(tf_siteName.getText(), tf_url.getText(), tf_id.getText(), tf_passwd.getText(),
						((SiteCategory) list_categ.getSelectedItem()).getSiteCategory(),
						(String) list_prefer.getSelectedItem(), ta_memo.getText());

				if (btn.getText().equals("입력(I)")) { // 입력버튼 클릭시
					// 추가 입력 : 중복된 url은 기존내용을 덮어씀
					Iterator it = siteInfoList.getList().iterator();
					while (it.hasNext()) {
						SiteInfo si = (SiteInfo) (it.next());
						if (si.getUrl().equals(tf_url.getText())) {

							si.setSiteName(tf_siteName.getText());
							si.setUrl(tf_url.getText());
							si.setId(tf_id.getText());
							si.setPasswd(tf_passwd.getText());
							si.setCateg(((SiteCategory) (list_categ.getSelectedItem())).getSiteCategory());
							si.setPrefer((String) list_prefer.getSelectedItem());
							si.setMemo(ta_memo.getText());

							mTable.setModel(new InfoTableModel(siteInfoList));

							tf_siteName.setText("");
							tf_url.setText("");
							tf_id.setText("");
							tf_passwd.setText("");
							list_categ.setSelectedIndex(0);
							list_prefer.setSelectedIndex(0);
							ta_memo.setText("");

							return;
						}
					}

					siteInfoList.addList(s);

					tf_siteName.setText("");
					tf_url.setText("");
					tf_id.setText("");
					tf_passwd.setText("");
					list_categ.setSelectedIndex(0);
					list_prefer.setSelectedIndex(0);
					ta_memo.setText("");

					reWrite.setEnabled(false);
					Input.setEnabled(false);

					sizeLabel.setText((++siteSize) + "개의 사이트가 등록되었습니다.");

				} else { // 수정버튼 클릭시
							// InfoTableModel model = (InfoTableModel)(mTable.getModel());
					siteInfo.setSiteName(tf_siteName.getText());
					siteInfo.setUrl(tf_url.getText());
					siteInfo.setId(tf_id.getText());
					if (!tf_passwd.getText().equals("")) {
						siteInfo.setPasswd(tf_passwd.getText());
					}
					siteInfo.setCateg(((SiteCategory) (list_categ.getSelectedItem())).getSiteCategory());
					// siteInfo.setCateg((String) list_categ.getSelectedItem());
					siteInfo.setPrefer((String) list_prefer.getSelectedItem());
					siteInfo.setMemo(ta_memo.getText());

					tf_siteName.setText("");
					tf_url.setText("");
					tf_id.setText("");
					tf_passwd.setText("");
					list_categ.setSelectedIndex(0);
					list_prefer.setSelectedIndex(0);
					ta_memo.setText("");

					tf_url.setEditable(true); // 주소 입력란 비활성화
					reWrite.setEnabled(true); // [새로작성]버튼 비활성화

					Input.setText("입력(I)");
					Input.setMnemonic('I');
				}
				mTable.setModel(new InfoTableModel(siteInfoList));

			}

		});

		reWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf_siteName.setText("");
				tf_url.setText("");
				tf_id.setText("");
				tf_passwd.setText("");
				list_categ.setSelectedIndex(0);
				list_prefer.setSelectedIndex(0);
				ta_memo.setText("");

			}

		});
	}

	// 사이트목록&등록현황 탭 구성 함수 : BorderLayout.CENTER
	private JTabbedPane Tab() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP); // 탭 왼쪽 정렬

		pane.addTab("사이트 목록", SiteListTab()); // '사이트 목록' 탭
		pane.addTab("등록현황", RigiStatusTab()); // '등록현황' 탭

		return pane;
	}

	// '사이트 목록' 탭 내부 구성 함수
	private JPanel SiteListTab() {
		JPanel totalPanel = new JPanel(new BorderLayout());
		JPanel listPanel = new JPanel(new BorderLayout());
		listPanel.setBorder(BorderFactory.createTitledBorder("검색/정렬"));

		// 검색, 정렬 패널을 p1에 부착.
		JPanel p1 = new JPanel(new BorderLayout());

		JPanel searchPanel = new JPanel(new FlowLayout());
		searchPanel.setBorder(BorderFactory.createTitledBorder("검색"));
		// String[] listData1 = { "전체", "일반", "정보", "포털", "학교" };
		list_search = new JComboBox<SiteCategory>(v2);

		list_search.setSelectedItem(total_category);
		searchPanel.add(list_search);
		searchPanel.add(new JLabel("필터: "));
		searchField = new JTextField(7);
		searchPanel.add(searchField);

		// 필터링을 위한 아이템 리스너 등록
		list_search.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SiteCategory cate = (SiteCategory) e.getItem();
				SiteInfoList list = new SiteInfoList();
				SiteInfo site;

				if (cate.getSiteCategory().equals("전체")) {
					mTable.setModel(new InfoTableModel(siteInfoList));
					return;
				}

				Iterator it = siteInfoList.getList().iterator();
				while (it.hasNext()) {
					site = (SiteInfo) it.next();
					String siteCate = site.getCateg();
					if (cate.getSiteCategory().equals(siteCate)) {
						list.addList(site);
					}
				}

				mTable.setModel(new InfoTableModel(list));

			}

		});

		searchField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = searchField.getText();
				SiteInfoList list = new SiteInfoList();
				SiteInfo site;

				if (s.equals("")) {
					mTable.setModel(new InfoTableModel(siteInfoList));
					return;
				}

				Iterator it = siteInfoList.getList().iterator();
				while (it.hasNext()) {
					site = (SiteInfo) it.next();
					String cate = site.getCateg();
					String name = site.getSiteName();
					String url = site.getUrl();

					if (cate.contains(s)) {
						list.addList(site);
						continue;
					}
					if (name.contains(s)) {
						list.addList(site);
						continue;
					}
					if (url.contains(s)) {
						list.addList(site);
						continue;
					}
				}

				mTable.setModel(new InfoTableModel(list));
			}

		});

		JPanel sortPanel = new JPanel(new FlowLayout());
		sortPanel.setBorder(BorderFactory.createTitledBorder("정렬"));

		// String[] listData2 = { "전체", "일반", "정보", "포털", "학교" };
		sort_categ = new JComboBox<SiteCategory>(v2);
		sort_categ.setSelectedItem(total_category);

		String[] listData3 = { "사이트 이름", "사이트 주소", "선호도" };
		sort_name = new JComboBox<String>(listData3);
		sort_name.setSelectedIndex(0);

		sortButton = new JButton("정렬");
		defaultButton = new JButton("기본");

		sortPanel.add(sort_categ);
		sortPanel.add(sort_name);
		sortPanel.add(sortButton);
		sortPanel.add(defaultButton);

		p1.add(searchPanel, BorderLayout.WEST);
		p1.add(sortPanel, BorderLayout.CENTER);

		// 표를 p2에 부착.
		JPanel p2 = new JPanel(new BorderLayout());
		mTable = new JTable();
		registerTableListener();
		p2.add(new JScrollPane(mTable), BorderLayout.CENTER);

		listPanel.add(p1, BorderLayout.NORTH);
		listPanel.add(p2, BorderLayout.CENTER);

		// 계정정보보기
		JPanel listPanel2 = new JPanel(new BorderLayout());
		JPanel p3 = new JPanel();
		cb = new JCheckBox("계정정보 보기");
		p3.add(cb);
		p3.add(new JLabel(" 아이디"));
		id = new JTextField(10);
		p3.add(id);
		p3.add(new JLabel(" 비밀번호"));
		pass = new JTextField(10);
		p3.add(pass);

		id.setEditable(false);
		pass.setEditable(false);

		JPanel p4 = new JPanel();
		deleteButton = new JButton("삭제");
		deleteButton.setEnabled(false);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// [삭제] 버튼 누르면 표에서 삭제
				siteInfoList.removeList(siteInfo);
				mTable.setModel(new InfoTableModel(siteInfoList));

				// [입력/수정] 항목 초기화
				tf_siteName.setText("");
				tf_url.setText("");
				tf_id.setText("");
				tf_passwd.setText("");
				list_categ.setSelectedIndex(0);
				list_prefer.setSelectedIndex(0);
				ta_memo.setText("");

				siteSize--;
				sizeLabel.setText(siteSize + "개의 사이트가 등록되었습니다.");
			}

		});
		p4.add(deleteButton);

		listPanel2.add(p3, BorderLayout.WEST);
		listPanel2.add(p4, BorderLayout.EAST);

		totalPanel.add(listPanel, BorderLayout.CENTER);
		totalPanel.add(listPanel2, BorderLayout.SOUTH);

		cb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					id.setText("");
					pass.setText("");
				}

			}

		});

		return totalPanel;
	}

	private void registerTableListener() {
		mTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// 표의 행 클릭시 [입력/수정]에 정보 나타내기
				int row = mTable.getSelectedRow();

				InfoTableModel info = (InfoTableModel) (mTable.getModel());
				siteInfo = info.getSiteInfo(row);

				tf_siteName.setText(siteInfo.getSiteName());
				tf_url.setText(siteInfo.getUrl());
				tf_id.setText(siteInfo.getId());
				tf_passwd.setText("");
				// list_categ.setSelectedItem(siteInfo.getCateg());
				int cateIndex = -1;
				Iterator it = v.iterator();
				while (it.hasNext()) {
					cateIndex++;
					SiteCategory sCate = (SiteCategory) it.next();
					if (sCate.getSiteCategory().equals(siteInfo.getCateg())) {
						break;
					}
				}
				list_categ.setSelectedIndex(cateIndex);
				list_prefer.setSelectedItem(siteInfo.getPrefer());
				ta_memo.setText(siteInfo.getMemo());

				// 표의 행 클릭하고 [계정정보보기] 체크시, 정보 나타내기
				if (cb.isSelected()) {
					id.setText(siteInfo.getId());
					pass.setText(siteInfo.getPasswd());
				}

				Input.setText("수정(E)");
				Input.setEnabled(true);
				Input.setMnemonic('E');

				deleteButton.setEnabled(true);
				tf_url.setEditable(false); // 주소 입력란 비활성화
				reWrite.setEnabled(false); // [새로작성]버튼 비활성화
			}

		});
	}

	// '등록현황' 탭 내부 구성 함수
	private JComponent RigiStatusTab() {

		can = new GraphCanvas();
		return can;

	}

	// 등록현황 안내 패널 함수 : BorderLayout.SOUTH
	private JPanel RegiStatus() {
		siteSize = siteInfoList.getSize();
		regiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sizeLabel = new JLabel(siteSize + "개의 사이트가 등록되었습니다.");
		regiPanel.add(sizeLabel);
		regiPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		return regiPanel;
	}

}