package com.icomment.icomment.util;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.icomment.icomment.domain.InvalidateToken;
import com.icomment.icomment.service.InvalidateTokenService;

@Component /*Class to delete the tokens that were already more than 1 hour in the bdd*/
public class DeleteTokensSheduler {
	
	@Autowired
	private InvalidateTokenService invalidateTokenService;
	
	@Autowired
	private LocalDateTimeUtil localDateTimeUtil;
	
	@Scheduled(cron="0 0 * * * *") //once 1 hour
	public void deleteTokens() throws Exception {
		Collection<InvalidateToken> invTokens= invalidateTokenService.getAll();
	
		if(!invTokens.isEmpty()) {
			LocalDateTime now = LocalDateTime.now();
			invTokens.forEach(invToken -> invToken.setInvalidateLocalDateTime(localDateTimeUtil.getLocalDateTimeFrom(invToken.getInvalidateDate())));
			Collection<InvalidateToken> tokensToDelete = invTokens.stream()
					.filter(invToken -> localDateTimeUtil.compare(now,invToken.getInvalidateLocalDateTime().plusHours(1L))) 
					.collect(Collectors.toList());
			//If now < invToken , it's because the record has NOT been in the database for 1 hour yet
			//If now > invToken , it is because the record was already 1 hour in the database
			if(!tokensToDelete.isEmpty()) {
				invalidateTokenService.deleteAll(tokensToDelete);
				System.out.println("Tokens with more than 1 hour in bdd deleted ");
				return;
			}
			System.out.println("There are not Tokens with more than 1 hour in bdd ");
			return;
		}
		
		System.out.println("There are not Tokens in bdd ");
	}
}
