package ysh.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Book;
import ysh.library.domain.Member;
import ysh.library.domain.Rent;
import ysh.library.domain.RentBook;
import ysh.library.repository.BookRepository;
import ysh.library.repository.MemberRepository;
import ysh.library.repository.RentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    /**
     * 대여
     */
    @Transactional
    public Long rent(Long memberId, Long bookId){
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);

        RentBook rentBook = RentBook.createRentBook(book);
        Rent rent = Rent.createRent(member, rentBook);

        rentRepository.save(rent);

        return rent.getId();
    }

    /**
     * 책 반납
     */
    @Transactional
    public void returnBook(Long rentId){
        Rent rent =rentRepository.findOne(rentId);
        rent.returnBook();
    }


}
