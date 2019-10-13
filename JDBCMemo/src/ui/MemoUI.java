package ui;

import java.util.ArrayList;
import java.util.Scanner;

import manager.MemoManager;
import vo.Book;
import vo.Review;
import vo.UserAccount;
import vo.UserMemo;

public class MemoUI {

	private MemoManager manager = new MemoManager(); // 요청과 관련된 처리를 하기 위해 생성한
														// MemoManager 클래스의 객체
	private Scanner sc = new Scanner(System.in);
	private Scanner scLine = new Scanner(System.in);
	private boolean accountflag = true;
	private boolean memoflag = true;

	private boolean managerflag = true;			
	private boolean memberflag = true;
	private boolean thanksflag = true;
	
	/**
	 * <pre>
	 * 메모 관리 프로그램의 사용자 화면을 구성하고 사용자 입력을 대기한다.
	 * 프로그램은 종료 메뉴를 선택하기 전까지 무한 반복하여 처리 된다.
	 * </pre>
	 */
	UserAccount login = null;
	public MemoUI() {
	
		while (accountflag) {
			try {
				memoflag = true;
				thanksflag = true;
				mainMenu();

				switch (sc.nextInt()) {
				case 1:
					insertAccount();
					break;
				case 2:
					deleteAccount();
					break;
				case 3:
					login = loginAccount();
						
					while (memoflag) {
						subMenu();
						switch (sc.nextInt()) {
						case 1:
							insertMemo();
							break;
						case 2:
							searchMemo();
							break;
						case 3:
							searchAllMemo();
							break;
						case 4:
							searhOneMemo();
							break;
						case 5:
							updateMemo();
							break;
						case 6:
							deleteMemo();
							break;
						case 7:
							while(thanksflag) {
								managerflag = true;
								memberflag = true;
								libraryMenu();
								switch(sc.nextInt()) {
								case 1:
									while(managerflag) {
										int password;
										System.out.print("관리자 비밀번호 : ");
										password = sc.nextInt();
										if(password == 1234) {
											System.out.println("등록성공!");
										} else {
											System.out.println("계정 오류!! 돌아가세요");
											break;
										}
										managerMenu();
										switch(sc.nextInt()) {
										case 1:
											bookRegist(); 
											break;
										case 2:
											stateCheck();
											break;
										case 3:
											checkAll();
											break;
										case 4:
											bookUpdate();
											break;
										case 5:
											bookDelete();
											break;
										case 0:
											managerflag = false;
											break;
										}
									}
									break;
								case 2:
									while(memberflag) {
										memberMenu();
										switch(sc.nextInt()) {
										case 1:
											checkAll();
											break;
										case 2:
											searchBook();
											break;
										case 3:
											bookRental();
											break;
										case 4:
											bookBooking();
											break;
										case 5:
											bookReturn();
											break;
										case 6:
											insertReview(login.getId());
											break;
										case 7:
											bookReview(login.getId());
											break;	
										case 8:
											deleteReview(login.getId());
											break;		
										case 0:
											memberflag = false;
											break;
										}
									}
									break;
								case 0:
									thanksflag = false;
									break;
								}
							}
							break;
						case 9:
							memoflag = false;
							break;
						}
					}
					break;
				case 9:
					accountflag = false;
					System.out.println("시스템이 종료되었습니다.");
					System.exit(0);
					
				}
			} catch(Exception e) {
				System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
				sc.nextLine();
				continue;
			} 
		}
	}
	
	private void deleteReview(String writer) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("글쓴이 입력  => ");
			writer = scLine.nextLine();
			if ((writer == null || writer.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				boolean result = manager.deleteReview(writer);
				if (result) {
					System.out.println("삭제에 성공했습니다.");
					break;
				} else {
					System.out.println("******ERROR 메모삭제에 실패했습니다.");
				}
			}
		}
	}

	private void bookReview(String bName) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("책 이름  => ");
			bName = scLine.nextLine();
			if ((bName == null || bName.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				ArrayList<Review> result = manager.bookReview(bName);
				if (result != null) {
					for(Review view : result)
						System.out.println(view);
					break;
				} else {
					System.out.println("******ERROR 상태검색에 실패했습니다.");
				}
			}
		}
	}

	private void insertReview(String writer) {
		String bName = null;
		
		try {
			while(true) {
				System.out.print("글쓴이 => ");
				writer = scLine.nextLine();
				System.out.print("책번호 => ");
				String bNum = scLine.nextLine();
				System.out.print("내용 => ");
				String content = scLine.nextLine();
				
				boolean result = manager.insertReview(new Review(writer, bName, bNum, content, null));
				if (result) {
					System.out.println("리뷰등록 완료");
					break;
				} else {
					System.out.println("******ERROR 리뷰등록에 실패했습니다.");
				}
			}	
		} catch (Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void bookReturn() {
		// TODO Auto-generated method stub
		String bName = null;
		String writer = null;
		String type = null;
		String state = null;
		try { 
			while (true) {
				System.out.println("반납할 책 번호 => ");
				String bNum = scLine.nextLine();
				if ((bNum == null || bNum.equals(""))) {
					System.out.println("******ERROR 다시입력해주세요.");
				} else {
					state = "보유중";
					boolean result = manager.changeState(bNum, new Book(bNum, bName, writer, type, state));
					if (result) {
						System.out.println(result);
						System.out.println("수정 완료");
						break;
					} else {
						System.out.println("******ERROR 상태 수정에 실패했습니다.");
					}
				}
			}
		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void bookBooking() {
		// TODO Auto-generated method stub
		String bName = null;
		String writer = null;
		String type = null;
		String state = null;
		try { 
			while (true) {
				System.out.println("예약할 책 번호 => ");
				String bNum = scLine.nextLine();

				state = "예약중";
				boolean result = manager.changeState(bNum, new Book(bNum, bName, writer, type, state));
				if (result) {
					System.out.println(result);
					System.out.println("수정 완료");
					break;
				} else {
					System.out.println("******ERROR 상태 수정에 실패했습니다.");
				}
			}

		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void bookRental() {
		String bName = null;
		String writer = null;
		String type = null;
		String state = null;
		try { 
			while (true) {
				System.out.println("대여할 책 번호 => ");
				String bNum = scLine.nextLine();
				if ((bNum == null || bNum.equals(""))) {
					System.out.println("******ERROR 다시입력해주세요.");
				} else {
					state = "대여중";
					boolean result = manager.changeState(bNum, new Book(bNum, bName, writer, type, state));
					if (result) {
						System.out.println(result);
						System.out.println("수정 완료");
						break;
					} else {
						System.out.println("******ERROR 상태 수정에 실패했습니다.");
					}
				}
			}
		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void searchBook() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				System.out.println("종류 또는 작가 입력 => ");
				String word = scLine.nextLine();
				ArrayList<Book> result = manager.searchBook(word);
				if (result != null) {
					printAllBook(result);
					break;
				} else {
					System.out.println("******ERROR 검색에 실패했습니다.");
				}
			}
		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void bookDelete() {
		// TODO Auto-generated method stub
		try {
		while (true) {
			System.out.println("삭제할 책 번호를 입력 해주세요 => ");
			String bNum = scLine.nextLine();
			
				boolean result = manager.bookDelete(bNum);
				if (result) {
					System.out.println("삭제에 성공했습니다.");
					break;
				} else {
					System.out.println("******ERROR 삭제에 실패했습니다.");
				}
			}
		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void bookUpdate() {
		// TODO Auto-generated method stub
		String bName = null;
		String writer = null;
		String type = null;
		String state = null;

		try { 
			while (true) {
				System.out.println("수정할 책 번호 => ");
				String bNum = scLine.nextLine();
				if ((bNum == null || bNum.equals(""))) {
					System.out.println("******ERROR 다시입력해주세요.");
				} else {
					System.out.println("책 이름 => ");
					bName = scLine.next();
					System.out.println("작가 => ");
					writer = scLine.next();
					System.out.println("종류 => ");
					type = scLine.next();
					switch(type) {
					case "1" :
						type = "국내소설";
						break;
					case "2" :
						type = "외국소설";
						break;
					case "3" :
						type = "전문서";
						break;
					case "4" :
						type = "인문/교습서";
						break;	
					case "5" :
						type = "자기개발";
						break;	
					default  :
						System.out.println("오류!! 다시 입력하세요");
						sc.nextLine();
						return;
					}
					System.out.println("상태 입력 => ");
					int answer = scLine.nextInt();
					switch(answer) {
					case 1: 
						state = "보유중";
						break;
					case 2: 
						state = "대여중";
						break;	
					case 3: 
						state = "예약중";
						break;
					case 4: 
						state = "분실";
						break;	
					default :
						System.out.println("오류!! 다시 입력하세요");
						sc.nextLine();
						return;
					}
					boolean result = manager.bookUpdate(bNum, new Book(bNum, bName, writer, type, state));
					if (result) {
						System.out.println("상태 수정 완료");
						break;
					} else {
						System.out.println("******ERROR 상태 수정에 실패했습니다.");
					}
				}
			}
		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void checkAll() {
		// TODO Auto-generated method stub
		while (true) {
			ArrayList<Book> result = manager.checkAll(); 
			if (result != null) {
				printAllBook(result);
				break;
			} else {
				System.out.println("******ERROR 조회에 실패했습니다.");
			}
		}
	}
	
	public void printAllBook(ArrayList<Book> books) {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	private void stateCheck() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("책 번호  => ");
			String bNum = scLine.nextLine();
			if ((bNum == null || bNum.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				Book result = manager.stateCheck(bNum);
				if (result != null) {
					System.out.println("상태 : "+result.getState());
					break;
				} else {
					System.out.println("******ERROR 상태검색에 실패했습니다.");
				}
			}
		}
	}

	private void bookRegist() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				System.out.println("책 이름 => ");
				String bName = scLine.nextLine();
				System.out.println("작가 => ");
				String writer = scLine.nextLine();
				System.out.println("종류 => ");
				String type = scLine.nextLine();
				switch(type) {
				case "1" :
					type = "국내소설";
					break;
				case "2" :
					type = "외국소설";
					break;
				case "3" :
					type = "전문서";
					break;
				case "4" :
					type = "인문/교습서";
					break;	
				case "5" :
					type = "자기개발";
					break;	
				default  :
					System.out.println("오류!! 다시 입력하세요");
					sc.nextLine();
					return;
				}
				System.out.println("상태 => ");
				String state = scLine.nextLine();
				switch(state) {
				case "1": 
					state = "보유중";
					break;
				case "2": 
					state = "대여중";
					break;	
				case "3": 
					state = "예약중";
					break;
				default :
					System.out.println("오류!! 다시 입력하세요");
					sc.nextLine();
					return;
				}
				boolean result = manager.bookRegist(new Book(null, bName, writer, type, state));
				if (result) {
					System.out.println("책 등록 성공!");
					break;
				} else {
					System.out.println("******ERROR 책 등록에 실패했습니다.");
				}
			}
		} catch(Exception e) {
			System.out.println("** 입력 데이터 오류. 처음으로 돌아갑니다.");
			sc.nextLine();
			return;
		}
	}

	private void memberMenu() {				// 회원 기능
		System.out.println("---------------------------------");
		System.out.println(" <<  회원 프로그램  >>"	    ); 
		System.out.println("---------------------------------");
		System.out.println("	1. 도서 전체 검색"); 
		System.out.println("	2. 도서  검색(종류/작가)");
		System.out.println("	3. 도서 대여"); 
		System.out.println("	4. 도서 예약"); 
		System.out.println("	5. 도서 반납");
		System.out.println("	6. 책 감상평(등록)");
		System.out.println("	7. 책 감상평(검색)");
		System.out.println("	8. 책 감상평(삭제)");
		System.out.println("	0. 종  료");
		System.out.println("---------------------------------");
		System.out.print(">> 선 택 : ");
		
	}

	private void managerMenu() {			// 관리자 기능
		System.out.println("---------------------------------");
		System.out.println(" <<  관리자 프로그램  >>"	    ); 
		System.out.println("---------------------------------");
		System.out.println("	1. 도서 등록"); 
		System.out.println("	2. 도서 검색(상태)"); 
		System.out.println("	3. 도서 전체 검색");
		System.out.println("	4. 도서 상태 수정"); 
		System.out.println("	5. 도서 목록 삭제");
		System.out.println("	0. 종  료");
		System.out.println("---------------------------------");
		System.out.print(">> 선 택 : ");
		
	}

	private void libraryMenu() {			// 새로운 기능을 넣을 프로그램 시작 페이지
		System.out.println("---------------------------------");
		System.out.println("<<  도서관 관리/대여 프로그램  >>"	  ); 
		System.out.println("---------------------------------");
		System.out.println("	1. 관 리 자용"); 
		System.out.println("	2. 회 원용"); 
		System.out.println("	0. 종     료"); 
		System.out.println("---------------------------------");
		System.out.print(">> 선 택 : ");
		
	}

	public void mainMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Memo   계정          │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.회원가입                                            │");
		System.out.println("│2.회원탈퇴                                            │");
		System.out.println("│3.로그인                                               │");
		System.out.println("│9.종료                                                  │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}
	
	public void subMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Memo   메모          │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.메모등록                                            │");
		System.out.println("│2.메모검색(내용+제목)             │");
		System.out.println("│3.메모전체보기                                      │");
		System.out.println("│4.메모상세보기                                      │");
		System.out.println("│5.메모수정                                            │");
		System.out.println("│6.메모삭제                                            │");
		System.out.println("│7.도서관리                                            │");
		System.out.println("│9.종료                                                  │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" 메뉴 번호를 선택하세요=> ");
	}

	public UserAccount loginAccount() {
		UserAccount login= null;
		while (true) {
			System.out.println("ID를 입력 해주세요 => ");
			String id = scLine.nextLine();
			System.out.println("비밀번호를 입력 해주세요 => ");
			String password = scLine.nextLine();
			if ((id == null || id.equals("")) || (password == null || password.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				UserAccount result = manager.loginAccount(new UserAccount(null, id, password));
				if (result != null) {
					login = result;
					System.out.println("로그인 완료 " + manager.getId() + "(" + manager.getName() + "님) 환영합니다.");
					break;
				} else {
					System.out.println("******ERROR 아이디/비밀번호가 일치하지 않습니다.");
				}
			}
		}
		return login;
	}

	public void insertAccount() {
		while (true) {
			System.out.println("이름을 입력 해주세요 => ");
			String name = scLine.nextLine();
			System.out.println("ID를 입력 해주세요 => ");
			String id = scLine.nextLine();
			System.out.println("비밀번호를 입력 해주세요 => ");
			String password = scLine.nextLine();
			if ((name == null || name.equals("")) || (id == null || id.equals(""))
					|| (password == null || password.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				boolean result = manager.insertAccount(new UserAccount(name, id, password));
				if (result) {
					System.out.println("계정등록 완료");
					break;
				} else {
					System.out.println("******ERROR 같은아이디가 존재합니다.");
				}
			}
		}
	}

	public void deleteAccount() {
	
		while (true) {
			System.out.println("ID를 입력 해주세요 => ");
			String id = scLine.nextLine();
			System.out.println("비밀번호를 입력 해주세요 => ");
			String password = scLine.nextLine();
			if ((id == null || id.equals("")) || (password == null || password.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				manager.deleteAllMemo(id);
				manager.deleteReview(id);
				boolean result = manager.deleteAccount(new UserAccount(null, id, password));
				if (result) {
					System.out.println("계정삭제 완료");
					break;
				} else {
					System.out.println("******ERROR 삭제실패.");
				}
			}
		}
	}

	public void insertMemo() {

		while (true) {
			System.out.println("제목을 입력 해주세요 => ");
			String title = scLine.nextLine();
			System.out.println("내용을 입력 해주세요 => ");
			String content = scLine.nextLine();
			if ((title == null || title.equals("")) || (content == null || content.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				boolean result = manager.insertMemo(new UserMemo(null, title, content, null, null));
				if (result) {
					System.out.println("메모등록 완료");
					break;
				} else {
					System.out.println("******ERROR 메모등록에 실패했습니다.");
				}
			}
		}
	}

	public void searchMemo() {
		while (true) {
			System.out.println("제목또는 내용을 입력 해주세요 => ");
			String searchWord = scLine.nextLine();
			if ((searchWord == null || searchWord.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				ArrayList<UserMemo> result = manager.searchMemo(searchWord);
				if (result != null) {
					printAllMemo(result);
					break;
				} else {
					System.out.println("******ERROR 메모검색에 실패했습니다.");
				}
			}
		}
	}

	public void searchAllMemo() {
		while (true) {

			ArrayList<UserMemo> result = manager.getMemoList();
			if (result != null) {
				printAllMemo(result);
				break;
			} else {
				System.out.println("******ERROR 메모조회에 실패했습니다.");
			}

		}
	}

	public void searhOneMemo() {
		while (true) {
			System.out.println("메모 일련번호를 입력 해주세요 => ");
			String memoseq = scLine.nextLine();
			if ((memoseq == null || memoseq.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				UserMemo result = manager.searchOneMemo(memoseq);
				if (result != null) {
					System.out.println("제목 : "+result.getTitle());
					System.out.println("내용 : "+result.getContent());
					break;
				} else {
					System.out.println("******ERROR 메모검색에 실패했습니다.");
				}
			}
		}
	}

	public void deleteMemo() {
		while (true) {
			System.out.println("삭제할 메모 일련번호를 입력 해주세요 => ");
			String memoseq = scLine.nextLine();
			if ((memoseq == null || memoseq.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				boolean result = manager.deleteMemo(memoseq);
				if (result) {
					System.out.println("삭제에 성공했습니다.");
					break;
				} else {
					System.out.println("******ERROR 메모삭제에 실패했습니다.");
				}
			}
		}
	}
	
	public void updateMemo(){
		while (true) {
			System.out.println("수정할 메모 일련번호를 입력 해주세요 => ");
			String memoseq = scLine.nextLine();
			if ((memoseq == null || memoseq.equals(""))) {
				System.out.println("******ERROR 다시입력해주세요.");
			} else {
				
				System.out.println("제목을 입력 해주세요 => ");
				String title = scLine.nextLine();
				System.out.println("내용을 입력 해주세요 => ");
				String content = scLine.nextLine();
				if ((title == null || title.equals("")) || (content == null || content.equals(""))) {
					System.out.println("******ERROR 다시입력해주세요.");
				} else {
					boolean result = manager.updateMemo(memoseq,new UserMemo(null, title, content, null, null));
					if (result) {
						System.out.println("메모수정 완료");
						break;
					} else {
						System.out.println("******ERROR 메모수정에 실패했습니다.");
					}
				}
			}
		}
	}

	public void printAllMemo(ArrayList<UserMemo> memos) {
		for (UserMemo memo : memos) {
			System.out.println(memo);
		}
	}
}
