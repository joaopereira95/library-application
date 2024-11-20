import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Offcanvas from 'react-bootstrap/Offcanvas';

function NavigationBar() {
 
  return (
    <>
        <Navbar expand={'sm'} className="bg-body-tertiary mb-3">
          <Container fluid>
            <Navbar.Brand href="/">Biblioteca</Navbar.Brand>
            <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-sm`} />
            <Navbar.Offcanvas
              id={`offcanvasNavbar-expand-sm`}
              aria-labelledby={`offcanvasNavbarLabel-expand-sm`}
              placement="end"
            >
              <Offcanvas.Header closeButton>
                <Offcanvas.Title id={`offcanvasNavbarLabel-expand-sm`}>
                  Biblioteca
                </Offcanvas.Title>
              </Offcanvas.Header>
              <Offcanvas.Body>
                <Nav className="justify-content-end flex-grow-1 pe-3">
                  <Nav.Link href="/">Início</Nav.Link>

                  <NavDropdown
                    title="Livros"
                    id={`offcanvasNavbarDropdown-expand-sm`}
                  >
                    <NavDropdown.Item href="/livros">
                      Biblioteca
                    </NavDropdown.Item>
                    <NavDropdown.Item href="/livros/recomendacoes">
                      Recomendações
                    </NavDropdown.Item>
                    <NavDropdown.Divider />
                    <NavDropdown.Item href="/livros/google-books">
                      Google Books
                    </NavDropdown.Item>
                  </NavDropdown>
                  
                  <Nav.Link href="/emprestimos">Empréstimos</Nav.Link>
                  <Nav.Link href="/usuarios">Usuários</Nav.Link>
                  
                </Nav>
                
              </Offcanvas.Body>
            </Navbar.Offcanvas>
          </Container>
        </Navbar>
    </>
  );
}

export default NavigationBar;