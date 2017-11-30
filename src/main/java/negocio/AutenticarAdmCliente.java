package negocio;

import persistencia.HibernateUtil;
import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;

public class AutenticarAdmCliente implements AuthenticationSuccessHandler {

	Session sessao1 = HibernateUtil.getSessionFactory().openSession();
	protected final Log logger = LogFactory.getLog(this.getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	protected AutenticarAdmCliente() {
		super();
	}

	// API
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	// IMPL
	protected void handle(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		final String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {

			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication) {
		boolean isCommon = false;
		boolean isAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_CLIENTE")) {
				isCommon = true;
				break;
			} else if (grantedAuthority.getAuthority().equals(
					"ROLE_ADMINISTRATOR")) {
				isAdmin = true;
				break;
			}
		}
		if (isCommon) {
			return "/cliente/finalizar_compra.xhtml";
		} else if (isAdmin) {
			return "/admin/principal.xhtml";
		} else {
			throw new IllegalStateException();
		}
	}

	public String getUsuarioLogado() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	/**
	 * Remove qualquer autenticação que estava antes armazenada na sessão.
	 */
	protected final void clearAuthenticationAttributes(
			final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	// Set<String> roles =
	// AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	// if (roles.contains("ROLE_ADMINISTRATOR")) {
	// System.out.println("ROLE_ADMINISTRATOR");
	// httpServletResponse.sendRedirect("/admin/principal.xhtml");
	// }
	// if (roles.contains("ROLE_CLIENTE")) {
	// System.out.println("ROLE_CLIENTE");
	// httpServletResponse.sendRedirect("/cliente/principal.xhtml");
	// }
	// sessao.close();
	// }

}
