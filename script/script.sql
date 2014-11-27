/**
 * Laboratório de Bases de Dados - Turma 3
 * Profa. Dra. Cristina D. A. Ciferri
 * Integrantes: Anderson Caio Santos Silva   	7972630
 * 				Rodrigo Rusa					7986970
 * Script Trabalho Prático 5
 */
/
/
/**
 *
 * SCRIPT DE CRIAÇÃO
 *
 */
/
-- Indica em que linguagem será executado o script
ALTER SESSION SET NLS_LANGUAGE= 'PORTUGUESE' NLS_TERRITORY= 'BRAZIL';
/
-- Evita problemas com a criação incorreta de sequências
ALTER SESSION SET deferred_segment_creation = FALSE;
/
-- Drop em todas as tabelas, eliminando também suas restrições
DROP TABLE evento CASCADE CONSTRAINTS;
DROP TABLE edicao CASCADE CONSTRAINTS;
DROP TABLE pessoa CASCADE CONSTRAINTS;
DROP TABLE inscrito CASCADE CONSTRAINTS;
DROP TABLE artigo CASCADE CONSTRAINTS;
DROP TABLE escreve CASCADE CONSTRAINTS;
DROP TABLE organiza CASCADE CONSTRAINTS;
DROP TABLE patrocinador CASCADE CONSTRAINTS;
DROP TABLE patrocinio CASCADE CONSTRAINTS;
DROP TABLE despesa CASCADE CONSTRAINTS;
DROP TABLE auxilio CASCADE CONSTRAINTS;
DROP TABLE relatorio;
/
-- Drop nas sequências
DROP SEQUENCE seq_evento;
DROP SEQUENCE seq_pessoa;
DROP SEQUENCE seq_artigo;
DROP SEQUENCE seq_despesa;
/
/
/**
 * Tabela Evento
 * @codEv							chave primária
 * @nomeEv							nome do evento (chave secundária)
 * @descricaoEv						descrição do evento
 * @websiteEv						endereço do website do evento
 * @totalArtigosApresentadosEv		número de artigos apresentados no evento (atributo derivado)
		Esse atributo é calculado pela soma do atributo 'qtdArtigosApresentadosEd' de cada tupla da tabela Edicao, que representa as edições do evento.
 * @PK_EVENTO						restrição de chave primária
 * @SK_EVENTO						restrição de chave secundária
 */
CREATE TABLE evento (
	codEv NUMBER NOT NULL,
	nomeEv VARCHAR2(100) NOT NULL,
	descricaoEv VARCHAR2(500),
	websiteEv VARCHAR2(30),
	totalArtigosApresentadosEv NUMBER,
	CONSTRAINT PK_EVENTO PRIMARY KEY (codEv),
	CONSTRAINT SK_EVENTO UNIQUE (nomeEv)
);
/
/
/**
 * Tabela Edicao
 * @codEv, numEd				chave primária
 * @descricaoEd					descrição da edição do evento
 * @dataInicioEd				data de início da edição do evento
 * @dataFimEd					data de término da edição do evento
 * @localEd						local de realização da edição do evento
 * @taxaEd						taxa de inscrição na edição do evento
 * @saldoFinanceiroEd			saldo financeiro da edição do evento (atributo derivado)
		Esse atributo é calculado somando os saldos dos patrocínios (atributo 'saldoPat' da tabela Patrocinio) e as taxas de inscrição pagas (contando o número de inscritos e multiplicando pela taxa de inscrição) referentes a essa edição do evento. A explicação de como é calculado o saldo dos patrocínios encontra-se na descrição da tabela Patrocinio.
 * @qtdArtigosApresentadosEd	quantidade de artigos apresentados na edição do evento (atributo derivado)
		Esse atributo é calculado contando o número de tuplas da tabela Artigo que tem como atributo 'numEd' (número da edição do evento) igual ao atributo 'numEd' dessa tabela (Edicao).
 * @PK_EDICAO					restrição de chave primária
 * @FK_EDICAO					restrição de chave estrangeira com a tabela Evento
 */
CREATE TABLE edicao (
	codEv NUMBER NOT NULL,
	numEd NUMBER NOT NULL,
	descricaoEd VARCHAR2(500),
	dataInicioEd DATE NOT NULL,
	dataFimEd DATE NOT NULL,
	localEd VARCHAR2(50) NOT NULL,
	taxaEd NUMBER(10,2) NOT NULL,
	saldoFinanceiroEd NUMBER(10,2),
	qtdArtigosApresentadosEd NUMBER,
	CONSTRAINT PK_EDICAO PRIMARY KEY (codEv, numEd),
	CONSTRAINT FK_EDICAO FOREIGN KEY (codEv) REFERENCES evento(codEv) ON DELETE CASCADE
);
/
/
/**
 * Tabela Pessoa
 * @idPe					chave primária
 * @nomePe					nome da pessoa
 * @emailPe					email da pessoa (chave secundária)
 * @instituicaoPe			instituição associada à pessoa
 * @telefonePe				número do telefone da pessoa
 * @nacionalidadePe			nacionalidade da pessoa
 * @enderecoPe				endereço da pessoa
 * @tipoOrganizador			armazena se a pessoa é organizadora ou não
 * @tipoParticipante		armazena se a pessoa é participante ou não
 * @tipoAutor				armazena se a pessoa é autora ou não
 * @PK_PESSOA				restrição de chave primária
 * @SK_PESSOA				restrição de chave secundária
 * @CH_PESSOA				checagem dos tipos de pessoa
 */
CREATE TABLE pessoa (
	idPe NUMBER NOT NULL,
	nomePe VARCHAR2(50) NOT NULL,
	emailPe VARCHAR2(30) NOT NULL,
	instituicaoPe VARCHAR2(100),
	telefonePe VARCHAR2(15),
	nacionalidadePe VARCHAR2(20),
	enderecoPe VARCHAR(100),
	tipoOrganizador CHAR(1) NOT NULL,
	tipoParticipante CHAR(1) NOT NULL,
	tipoAutor CHAR(1) NOT NULL,
	CONSTRAINT PK_PESSOA PRIMARY KEY (idPe),
	CONSTRAINT SK_PESSOA UNIQUE (emailPe),
	CONSTRAINT CH_PESSOA CHECK (UPPER(tipoOrganizador) IN ('S', 'N') AND UPPER(tipoParticipante) IN ('S', 'N') AND 
		UPPER(tipoAutor) IN ('S', 'N'))
);
/
/
/**
 * Tabela Inscrito
 * @codEv, numEd, idPart		chave primária				
 * @dataInsc					data de realização da inscrição
 * @tipoApresentador			armazena se o inscrito irá apresentar ou não
 * @PK_INSCRITO					restrição de chave primária
 * @FK_INSCRITO1				restrição de chave estrangeira com a tabela Edicao
 * @FK_INSCRITO2				restrição de chave estrangeira com a tabela Pessoa
 */
CREATE TABLE inscrito (
	codEv NUMBER NOT NULL,
	numEd NUMBER NOT NULL,
	idPart NUMBER NOT NULL,
	dataInsc DATE,
	tipoApresentador CHAR(1) NOT NULL,
	CONSTRAINT PK_INSCRITO PRIMARY KEY (codEv, numEd, idPart),
	CONSTRAINT FK_INSCRITO1 FOREIGN KEY (codEv, numEd) REFERENCES edicao(codEv, numEd) ON DELETE CASCADE,
	CONSTRAINT FK_INSCRITO2 FOREIGN KEY (idPart) REFERENCES pessoa(idPe) ON DELETE CASCADE,
	CONSTRAINT CH_INSCRITO CHECK (UPPER(tipoApresentador) IN ('S', 'N'))
);
/
/
/**
 * Tabela Artigo
 * @idArt					chave primária
 * @tituloArt				título do artigo
 * @dataApresArt			data da apresentação do artigo
 * @horaApresArt			hora da apresentação do artigo
 * @codEv					código do evento
 * @numEd					número da edição do evento
 * @idApr					número do apresentador
 * @PK_ARTIGO				restrição de chave primária
 * @FK_ARTIGO				restrição de chave estrangeira com a tabela Inscrito 
 */
CREATE TABLE artigo (
	idArt NUMBER NOT NULL,
	tituloArt VARCHAR2(200) NOT NULL,
	dataApresArt DATE,
	horaApresArt TIMESTAMP,
	codEv NUMBER NOT NULL,
	numEd NUMBER NOT NULL,
	idApr NUMBER NOT NULL,
	CONSTRAINT PK_ARTIGO PRIMARY KEY (idArt),
	CONSTRAINT FK_ARTIGO FOREIGN KEY (codEv, numEd, idApr) REFERENCES inscrito(codEv, numEd, idPart) ON DELETE CASCADE
);
/
/
/**
 * Tabela Escreve
 * @idAut, idArt				chave primária
 * @PK_ESCREVE					restrição de chave primária
 * @FK_ESCREVE1					restrição de chave estrangeira com a tabela Pessoa
 * @FK_ESCREVE2					restrição de chave estrangeira com a tabela Artigo
 */
CREATE TABLE escreve (
	idAut NUMBER NOT NULL,
	idArt NUMBER NOT NULL,
	CONSTRAINT PK_ESCREVE PRIMARY KEY (idAut, idArt),
	CONSTRAINT FK_ESCREVE1 FOREIGN KEY (idAut) REFERENCES pessoa(idPe) ON DELETE CASCADE,
	CONSTRAINT FK_ESCREVE2 FOREIGN KEY (idArt) REFERENCES artigo(idArt) ON DELETE CASCADE
);
/
/
/**
 * Tabela Organiza
 * @idOrg, codEv, numEd			chave primária
 * @cargoOrg					cargo do organizador
 * @PK_ORGANIZA					restrição de chave primária
 * @FK_ORGANIZA1				restrição de chave estrangeira com a tabela Pessoa
 * @FK_ORGANIZA2				restrição de chave estrangeira com a tabela Edicao
 */
CREATE TABLE organiza (
	idOrg NUMBER NOT NULL,
	codEv NUMBER NOT NULL,
	numEd NUMBER NOT NULL,
	cargoOrg VARCHAR2(50),
	CONSTRAINT PK_ORGANIZA PRIMARY KEY (idOrg, codEv, numEd),
	CONSTRAINT FK_ORGANIZA1 FOREIGN KEY (idOrg) REFERENCES pessoa(idPe) ON DELETE CASCADE,
	CONSTRAINT FK_ORGANIZA2 FOREIGN KEY (codEv, numEd) REFERENCES edicao(codEv, numEd) ON DELETE CASCADE
);
/
/
/**
 * Tabela Patrocinador
 * @cnpjPat						chave primária
 * @razaoSocialPat				razão social do patrocinador
 * @telefonePat					telefone do patrocinador
 * @enderecoPat					endereço do patrocinador
 * @PK_PATROCINADOR				restrição de chave primária
 */
CREATE TABLE patrocinador (
	cnpjPat VARCHAR2(14) NOT NULL,
	razaoSocialPat VARCHAR2(50) NOT NULL,
	telefonePat VARCHAR2(15),
	enderecoPat VARCHAR2(100),
	CONSTRAINT PK_PATROCINADOR PRIMARY KEY (cnpjPat)
);
/
/
/**
 * Tabela Patrocinio
 * @cnpjPat, codEv, numEd		chave primária
 * @valorPat					valor do patrocínio
 * @saldoPat					saldo do patrocínio (atributo derivado)
		Esse atributo é calculado subtraindo-se do atributo 'valorPat' dessa tabela (Patrocinio) a soma dos atributos 'valorDesp' da tabela Despesa e 'valorAux' da tabela Auxilio referentes a edição de oferecimento do patrocínio.
 * @PK_PATROCINIO				restrição de chave primária
 * @FK_PATROCINIO1				restrição de chave estrangeira com a tabela Patrocinador
 * @FK_PATROCINIO2				restrição de chave estrangeira com a tabela Edicao
 */
CREATE TABLE patrocinio (
	cnpjPat VARCHAR2(14) NOT NULL,
	codEv NUMBER NOT NULL,
	numEd NUMBER NOT NULL,
	valorPat NUMBER(10,2),
	saldoPat NUMBER(10,2),
	dataPat DATE,
	CONSTRAINT PK_PATROCINIO PRIMARY KEY (cnpjPat, codEv, numEd),
	CONSTRAINT FK_PATROCINIO1 FOREIGN KEY (cnpjPat) REFERENCES patrocinador(cnpjPat) ON DELETE CASCADE,
	CONSTRAINT FK_PATROCINIO2 FOREIGN KEY (codEv, numEd) REFERENCES edicao(codEv, numEd) ON DELETE CASCADE
);
/
/
/**
 * Tabela Despesa
 * @codDesp, codEv, numEd			chave primária
 * @cnpjPat							cnpj do patrocinador
 * @codEvPat						código do evento
 * @numEdPat						número da edição do evento
 * @dataDesp						data da despesa
 * @valorDesp						valor da despesa
 * @descricaoDesp					descriçao da despesa
 * @PK_DESPESA						restrição de chave primária
 * @FK_DESPESA1						restrição de chave estrangeira com a tabela Edicao
 * @FK_DESPESA2						restrição de chave estrangeira com a tabela Patrocinio
 */ 
CREATE TABLE despesa (
	codDesp NUMBER NOT NULL,
	codEv NUMBER NOT NULL,
	numEd NUMBER NOT NULL,
	cnpjPat VARCHAR2(14) NOT NULL,
	codEvPat NUMBER NOT NULL,
	numEdPat NUMBER NOT NULL,
	dataDesp DATE,
	valorDesp NUMBER(10,2),
	descricaoDesp VARCHAR2(200),
	CONSTRAINT PK_DESPESA PRIMARY KEY (codDesp, codEv, numEd),
	CONSTRAINT FK_DESPESA1 FOREIGN KEY (codEv, numEd) REFERENCES edicao(codEv, numEd) ON DELETE CASCADE,
	CONSTRAINT FK_DESPESA2 FOREIGN KEY (cnpjPat, codEvPat, numEdPat) REFERENCES patrocinio(cnpjPat, codEv, numEd) ON DELETE CASCADE
);
/ 
/ 
/**
 * Tabela Auxilio
 * @codEvApr, numEdApr, idApr, tipoAux		chave primária
 * @cnpjPat									cnpj do patrocinador
 * @codEvPat								código do evento
 * @numEdPat								número da edição do evento
 * @valorAux								valor do auxílio dado ao apresentador
 * @dataAux									data que foi dado o auxílio
 * @PK_AUXILIO								restrição de chave primária
 * @FK_AUXILIO1								restrição de chave estrangeira com a tabela Patrocinio
 * @FK_AUXILIO2								restrição de chave estrangeira com a tabela Inscrito
 * @CH_AUXILIO								checagem do tipo de auxílio
 */ 
CREATE TABLE auxilio (
	cnpjPat VARCHAR2(14) NOT NULL,
	codEvPat NUMBER NOT NULL,
	numEdPat NUMBER NOT NULL,
	codEvApr NUMBER NOT NULL,
	numEdApr NUMBER NOT NULL,
	idApr NUMBER NOT NULL,
	valorAux NUMBER(10,2),
	dataAux DATE,
	tipoAux VARCHAR2(15) NOT NULL,
	CONSTRAINT PK_AUXILIO PRIMARY KEY (codEvApr, numEdApr, idApr, tipoAux),
	CONSTRAINT FK_AUXILIO1 FOREIGN KEY (cnpjPat, codEvPat, numEdPat) REFERENCES patrocinio(cnpjPat, codEv, numEd) ON DELETE CASCADE,
	CONSTRAINT FK_AUXILIO2 FOREIGN KEY (codEvApr, numEdApr, idApr) REFERENCES inscrito(codEv, numEd, idPart) ON DELETE CASCADE,
	CONSTRAINT CH_AUXILIO CHECK (UPPER(tipoAux) IN ('HOSPEDAGEM', 'ALIMENTAÇÃO', 'TRANSPORTE'))
);
/
/
/**
 * Tabela Relatorio
 * @codEv						código do evento
 * @nomeEv						nome do evento
 * @numEd						número da edição do evento
 * @inscritos					número de inscritos na edição do evento
 * @artigosApresentados			número de artigos apresentados na edição do evento
 * @qtdPatrocinadores			número de patrocínios da edição
 * @valorTotalGanho				valor total ganho (patrocínio + inscrições) na edição
 * @valorTotalGasto				valor total gasto (despesas + auxílios) na edição
 * @saldo						saldo da edição (valor ganho - valor gasto)
 */
 
CREATE TABLE relatorio(
	codEv NUMBER,
	nomeEv VARCHAR2(100),
	numEd NUMBER,
	inscritos NUMBER,
	artigosApresentados NUMBER,
	qtdPatrocinadores NUMBER,
	valorTotalGanho NUMBER(10, 2),
	valorTotalGasto NUMBER(10, 2),
	saldo NUMBER(10, 2)
);
/
/
-- Cria sequência para codEv da tabela Evento
CREATE SEQUENCE seq_evento
START WITH 1
INCREMENT BY 1;
/
/
-- Cria sequência para idPe da tabela Pessoa
CREATE SEQUENCE seq_pessoa
START WITH 1
INCREMENT BY 1;
/ 
/ 
-- Cria sequência para idArt da tabela Artigo
CREATE SEQUENCE seq_artigo
START WITH 1
INCREMENT BY 1;
/
/
-- Cria sequência para codDesp da tabela Despesa
CREATE SEQUENCE seq_despesa
START WITH 1
INCREMENT BY 1;
/
/
/**
 * VIEWS
 */
-- View que imprime inscritos
CREATE OR REPLACE VIEW view_inscritos AS
SELECT codEv,
       nomeEv, 
	   numEd,
	   idPe,
	   nomePe, 
	   to_char(dataInsc, 'dd/mm/yyyy') AS "dataInsc", 
	   tipoApresentador
FROM pessoa JOIN inscrito ON (idPe = idPart) NATURAL JOIN edicao NATURAL JOIN evento
ORDER BY nomePe;
/
-- View que imprime artigos
CREATE OR REPLACE VIEW view_artigos AS
SELECT a.idArt AS "idArt", 
	   a.tituloArt AS "tituloArt", 
	   to_char(a.dataApresArt, 'dd/mm/yyyy') AS "dataApresArt", 
	   to_char(a.horaApresArt, 'hh24:mi') AS "horaApresArt",
	   ev.codEv AS "codEv",
	   ev.nomeEv AS "nomeEv",
	   ed.numEd AS "numEd",
	   p.idPe AS "idPe",
	   p.nomePe AS "nomePe"
FROM artigo a JOIN inscrito i ON (a.codEv = i.codEv AND a.numEd = i.numEd AND a.idApr = i.idPart) 
			JOIN pessoa p ON (i.idPart = p.idPe) 
			JOIN edicao ed ON (i.codEv = ed.codEv AND i.numEd = ed.numEd) 
			JOIN evento ev ON (ed.codEv = ev.codEv)
ORDER BY a.tituloArt;
/
-- View que imprime autores (Tabela escreve)
CREATE OR REPLACE VIEW view_escreve AS
SELECT idArt,
	   nomePe,
       emailPe,
	   instituicaoPe,
	   telefonePe
FROM escreve JOIN pessoa ON (idAut = idPe)
ORDER BY nomePe;
/
-- View que imprime organizadores
CREATE OR REPLACE VIEW view_organizadores AS
SELECT idOrg,
	   nomePe,
	   nomeEv,
	   numEd,
	   cargoOrg
FROM pessoa JOIN organiza ON (idPe = idOrg) NATURAL JOIN edicao NATURAL JOIN evento
ORDER BY nomePe;
/
-- View que imprime patrocínios
CREATE OR REPLACE VIEW view_patrocinios AS
SELECT cnpjPat,
	   razaoSocialPat,
	   nomeEv,
	   numEd,
	   valorPat,
	   saldoPat,
	   to_char(dataPat, 'dd/mm/yyyy') AS "dataPat"
FROM patrocinador NATURAL JOIN patrocinio NATURAL JOIN edicao NATURAL JOIN evento
ORDER BY razaoSocialPat;
/
-- View que imprime despesas
CREATE OR REPLACE VIEW view_despesas AS
SELECT d.codDesp AS "codDesp",
	   ev.nomeEv AS "nomeEv",
	   d.numEd AS "numEd",
	   p.cnpjPat AS "cnpjPat",
	   p.razaoSocialPat AS "razaoSocialPat",
	   to_char(d.dataDesp, 'dd/mm/yyyy') AS "dataDesp",
	   d.valorDesp AS "valorDesp",
	   d.descricaoDesp AS "descricaoDesp"
FROM patrocinador p JOIN patrocinio pat ON (p.cnpjPat = pat.cnpjPat) 
	 JOIN despesa d ON (pat.cnpjPat = d.cnpjPat AND pat.codEv = d.codEvPat AND pat.numEd = d.numEdPat) 
	 JOIN edicao ed ON (d.codEv = ed.codEv AND d.numEd = ed.numEd) 
	 JOIN evento ev ON (ed.codEv = ev.codEv)
ORDER BY ev.nomeEv, p.razaoSocialPat;
/
-- View que imprime auxílios
CREATE OR REPLACE VIEW view_auxilios AS
SELECT p.cnpjPat AS "cnpjPat",
	   p.razaoSocialPat AS "razaoSocialPat",
	   ev.nomeEv AS "nomeEv",
	   aux.codEvPat AS "codEvPat",
	   aux.numEdPat AS "numEdPat",
	   aux.codEvApr AS "codEvApr",
	   aux.numEdApr AS "numEdApr",
	   aux.idApr AS "idApr",
	   pe.nomePe AS "nomePe",
	   aux.valorAux AS "valorAux",
	   to_char(aux.dataAux, 'dd/mm/yyyy') AS "dataAux",
	   aux.tipoAux AS "tipoAux"
FROM patrocinador p JOIN patrocinio pat ON (p.cnpjPat = pat.cnpjPat)
	 JOIN auxilio aux ON (pat.cnpjPat = aux.cnpjPat AND pat.codEv = aux.codEvPat AND pat.numEd = aux.numEdPat)
	 JOIN inscrito i ON (aux.codEvApr = i.codEv AND aux.numEdApr = i.numEd AND aux.idApr = i.idPart)
	 JOIN pessoa pe ON (i.idPart = pe.idPe)
	 JOIN edicao ed ON (pat.codEv = ed.codEv AND pat.numEd = ed.numEd)
	 JOIN evento ev ON (ed.codEv = ev.codEv)
ORDER BY ev.nomeEv, p.razaoSocialPat;
/
-- View que imprime o relatório geral
SELECT codEv, 
	   numEd,
	   nomeEv,
       inscritos,
	   artigosApresentados,
	   qtdPatrocinadores,
	   valorTotalGanho,
	   valorTotalGasto,
	   saldo
FROM relatorio
ORDER BY nomeEv, numEd;
/
/
/**
 * TRIGGERS
 */
-- Trigger para controle do atributos derivados totalArtigosApresentadosEv e qtdArtigosApresentadosEd
CREATE OR REPLACE TRIGGER n_artigos_edicao_evento
FOR INSERT OR DELETE
ON artigo
COMPOUND TRIGGER

	codEv_old NUMBER;
	numEd_old NUMBER;
	codEv_new NUMBER;
	numEd_new NUMBER;

	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;

	BEFORE EACH ROW IS
	BEGIN
		codEv_old := :OLD.codEv;
		numEd_old := :OLD.numEd;
	END BEFORE EACH ROW;

	AFTER EACH ROW IS
	BEGIN
		codEv_new := :NEW.codEv;
		numEd_new := :NEW.numEd;
	END AFTER EACH ROW;
	
	AFTER STATEMENT IS
	BEGIN
		IF inserting THEN
			UPDATE edicao SET qtdArtigosApresentadosEd = qtdArtigosApresentadosEd + 1 
				WHERE codEv = codEv_new AND numEd = numEd_new;
			UPDATE evento SET totalArtigosApresentadosEv = totalArtigosApresentadosEv + 1 
				WHERE codEv = codEv_new;
		
		ELSIF deleting THEN
			UPDATE edicao SET qtdArtigosApresentadosEd = qtdArtigosApresentadosEd - 1 
				WHERE codEv = codEv_old AND numEd = numEd_old;
			UPDATE evento SET totalArtigosApresentadosEv = totalArtigosApresentadosEv - 1 
				WHERE codEv = codEv_old;
		END IF;
	END AFTER STATEMENT;

END;
/
-- Trigger para atualização do atributo derivado totalArtigosApresentadosEv de evento quando uma edição é removida
CREATE OR REPLACE TRIGGER atualiza_n_artigos_edicao
FOR DELETE
ON edicao
COMPOUND TRIGGER

	codEv_old NUMBER;
	qtdArtigosApresentadosEd NUMBER;
	
	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;

	BEFORE EACH ROW IS
	BEGIN
		codEv_old := :OLD.codEv;
		qtdArtigosApresentadosEd := :OLD.qtdArtigosApresentadosEd;
	END BEFORE EACH ROW;

	AFTER EACH ROW IS
	BEGIN
		NULL;
	END AFTER EACH ROW;

	AFTER STATEMENT IS
	BEGIN
		UPDATE evento SET totalArtigosApresentadosEv = totalArtigosApresentadosEv - qtdArtigosApresentadosEd 
			WHERE codEv = codEv_old;
	END AFTER STATEMENT;
	
END;
/
-- Trigger para atualização do atributo derivado totalArtigosApresentadosEv e qtdArtigosApresentadosEd quando um inscrito é removido
CREATE OR REPLACE TRIGGER atualiza_n_artigos_inscrito
FOR DELETE
ON inscrito
COMPOUND TRIGGER

	codEv_old NUMBER;
	numEd_old NUMBER;
	idPart_old NUMBER;
	n_artigos NUMBER;
	
	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;

	BEFORE EACH ROW IS
	BEGIN
		codEv_old := :OLD.codEv;
		numEd_old := :OLD.numEd;
	END BEFORE EACH ROW;

	AFTER EACH ROW IS
	BEGIN
		NULL;
	END AFTER EACH ROW;

	AFTER STATEMENT IS
	BEGIN
		UPDATE edicao SET qtdArtigosApresentadosEd = qtdArtigosApresentadosEd - 1 
			WHERE codEv = codEv_old AND numEd = numEd_old;
		UPDATE evento SET totalArtigosApresentadosEv = totalArtigosApresentadosEv - 1
			WHERE codEv = codEv_old;
	END AFTER STATEMENT;
	
END;
/
-- Trigger que atualiza o saldoPat e o saldoFinanceiroEd quando uma despesa é inserida ou removida
CREATE OR REPLACE TRIGGER saldoPat_despesa
FOR INSERT OR DELETE OR UPDATE
ON despesa
COMPOUND TRIGGER

	cnpjPat_old NUMBER;
	codEvPat_old NUMBER;
	numEdPat_old NUMBER;
	valorDesp_old NUMBER;
	
	cnpjPat_new NUMBER;
	codEvPat_new NUMBER;
	numEdPat_new NUMBER;
	valorDesp_new NUMBER;
	
	codEv_old NUMBER;
	numEd_old NUMBER;
	
	codEv_new NUMBER;
	numEd_new NUMBER;

	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;

	BEFORE EACH ROW IS
	BEGIN
		cnpjPat_old := :OLD.cnpjPat;
		codEvPat_old := :OLD.codEvPat;
		numEdPat_old := :OLD.numEdPat;
		valorDesp_old := :OLD.valorDesp;
		
		codEv_old := :OLD.codEv;
		numEd_old := :OLD.numEd;
	END BEFORE EACH ROW;
	
	AFTER EACH ROW IS
	BEGIN
		cnpjPat_new := :NEW.cnpjPat;
		codEvPat_new := :NEW.codEvPat;
		numEdPat_new := :NEW.numEdPat;
		valorDesp_new := :NEW.valorDesp;
		
		codEv_new := :NEW.codEv;
		numEd_new := :NEW.numEd;
	END AFTER EACH ROW;
	
	AFTER STATEMENT IS
	BEGIN
		IF inserting THEN
			-- Atualiza saldoPat
			UPDATE patrocinio SET saldoPat = saldoPat - valorDesp_new
				WHERE cnpjPat = cnpjPat_new AND codEv = codEvPat_new AND numEd = numEdPat_new;
			-- Atualiza saldoFinanceiroEd
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd - valorDesp_new
				WHERE codEv = codEv_new AND numEd = numEd_new;
		ELSIF deleting THEN
			-- Atualiza saldoPat
			UPDATE patrocinio SET saldoPat = saldoPat + valorDesp_old
				WHERE cnpjPat = cnpjPat_old AND codEv = codEvPat_old AND numEd = numEdPat_old;
			-- Atualiza saldoFinanceiroEd
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd + valorDesp_old
				WHERE codEv = codEv_old AND numEd = numEd_old;
		ELSE
			-- Atualiza saldoPat
			UPDATE patrocinio SET saldoPat = saldoPat + valorDesp_old - valorDesp_new
				WHERE cnpjPat = cnpjPat_old AND codEv = codEvPat_old AND numEd = numEdPat_old;
			-- Atualiza saldoFinanceiroEd
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd + valorDesp_old - valorDesp_new
				WHERE codEv = codEv_old AND numEd = numEd_old;
		END IF;
	END AFTER STATEMENT;
		
END;
/
-- Trigger que atualiza o saldoPat e o saldoFinanceiroEd quando uma auxílio é inserido ou removido
CREATE OR REPLACE TRIGGER saldoPat_auxilio
FOR INSERT OR DELETE OR UPDATE
ON auxilio
COMPOUND TRIGGER

	cnpjPat_old NUMBER;
	codEvPat_old NUMBER;
	numEdPat_old NUMBER;
	valorAux_old NUMBER;
	
	cnpjPat_new NUMBER;
	codEvPat_new NUMBER;
	numEdPat_new NUMBER;
	valorAux_new NUMBER;

	codEv_old NUMBER;
	numEd_old NUMBER;
	
	codEv_new NUMBER;
	numEd_new NUMBER;
	
	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;

	BEFORE EACH ROW IS
	BEGIN
		cnpjPat_old := :OLD.cnpjPat;
		codEvPat_old := :OLD.codEvPat;
		numEdPat_old := :OLD.numEdPat;
		valorAux_old := :OLD.valorAux;
		
		codEv_old := :OLD.codEvApr;
		numEd_old := :OLD.numEdApr;
	END BEFORE EACH ROW;
	
	AFTER EACH ROW IS
	BEGIN
		cnpjPat_new := :NEW.cnpjPat;
		codEvPat_new := :NEW.codEvPat;
		numEdPat_new := :NEW.numEdPat;
		valorAux_new := :NEW.valorAux;
		
		codEv_new := :NEW.codEvApr;
		numEd_new := :NEW.numEdApr;
	END AFTER EACH ROW;
	
	AFTER STATEMENT IS
	BEGIN
		IF inserting THEN
			-- Atualiza o saldoPat
			UPDATE patrocinio SET saldoPat = saldoPat - valorAux_new
				WHERE cnpjPat = cnpjPat_new AND codEv = codEvPat_new AND numEd = numEdPat_new;
			-- Atualiza o saldoFinanceiroEd
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd - valorAux_new
				WHERE codEv = codEv_new AND numEd = numEd_new;
		ELSIF deleting THEN
			-- Atualiza o saldoPat
			UPDATE patrocinio SET saldoPat = saldoPat + valorAux_old
				WHERE cnpjPat = cnpjPat_old AND codEv = codEvPat_old AND numEd = numEdPat_old;
			-- Atualiza o saldoFinanceiroEd
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd + valorAux_old
				WHERE codEv = codEv_old AND numEd = numEd_old;
		ELSE
			-- Atualiza o saldoPat
			UPDATE patrocinio SET saldoPat = saldoPat + valorAux_old - valorAux_new
				WHERE cnpjPat = cnpjPat_old AND codEv = codEvPat_old AND numEd = numEdPat_old;
			-- Atualiza o saldoFinanceiroEd
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd + valorAux_old - valorAux_new
				WHERE codEv = codEv_old AND numEd = numEd_old;
		END IF;
	END AFTER STATEMENT;
		
END;
/
-- Trigger que atualiza o saldoFinanceiroEd da edição quando um inscrito é inserido ou removido
CREATE OR REPLACE TRIGGER saldoFinanceiroEd_edicao
FOR INSERT OR DELETE
ON inscrito
COMPOUND TRIGGER
	
	codEv_old NUMBER;
	numEd_old NUMBER;
	codEv_new NUMBER;
	numEd_new NUMBER;
	taxa NUMBER(10, 2);
	
	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;
	
	BEFORE EACH ROW IS
	BEGIN
		codEv_old := :OLD.codEv;
		numEd_old := :OLD.numEd;
	END BEFORE EACH ROW;
	
	AFTER EACH ROW IS
	BEGIN
		codEv_new := :NEW.codEv;
		numEd_new := :NEW.numEd;
	END AFTER EACH ROW;
	
	AFTER STATEMENT IS
	BEGIN
		IF inserting THEN
			SELECT taxaEd
			INTO taxa
			FROM edicao
			WHERE codEv = codEv_new AND numEd = numEd_new;
		
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd + taxa
				WHERE codEv = codEv_new AND numEd = numEd_new;
		ELSIF deleting THEN
			SELECT taxaEd
			INTO taxa
			FROM edicao
			WHERE codEv = codEv_old AND numEd = numEd_old;
		
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd - taxa
				WHERE codEv = codEv_old AND numEd = numEd_old;
		END IF;
	END AFTER STATEMENT;
	
END;
/
-- Trigger que atualiza o saldoFinanceiroEd da edição quando um patrocinador é inserido ou removido
CREATE OR REPLACE TRIGGER saldoFinanceiroEd_patrocinio
FOR INSERT OR DELETE OR UPDATE
ON patrocinio
COMPOUND TRIGGER
	
	cnpjPat_old NUMBER;
	codEv_old NUMBER;
	numEd_old NUMBER;
	valorPat_old NUMBER(10, 2);
	codEv_new NUMBER;
	numEd_new NUMBER;
	valorPat_new NUMBER(10, 2);
	
	BEFORE STATEMENT IS
	BEGIN
		NULL;
	END BEFORE STATEMENT;
	
	BEFORE EACH ROW IS
	BEGIN
		cnpjPat_old := :OLD.cnpjPat;
		codEv_old := :OLD.codEv;
		numEd_old := :OLD.numEd;
		valorPat_old := :OLD.valorPat;
	END BEFORE EACH ROW;
	
	AFTER EACH ROW IS
	BEGIN
		codEv_new := :NEW.codEv;
		numEd_new := :NEW.numEd;
		valorPat_new := :NEW.valorPat;
	END AFTER EACH ROW;
	
	AFTER STATEMENT IS
	BEGIN
		IF inserting THEN
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd + valorPat_new
				WHERE codEv = codEv_new AND numEd = numEd_new;
		ELSIF deleting THEN
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd - valorPat_old
				WHERE codEv = codEv_old AND numEd = numEd_old;
		ELSE
			UPDATE edicao SET saldoFinanceiroEd = saldoFinanceiroEd - valorPat_old + valorPat_new
				WHERE codEv = codEv_old AND numEd = numEd_old;
		END IF;
	END AFTER STATEMENT;
	
END;
/
/
/**
 *
 * PROCEDIMENTOS
 *
 */
/
CREATE OR REPLACE PROCEDURE relatorio_eventos
IS
	-- Variáveis
	inscritos relatorio.inscritos%TYPE;
	artigosApresentados relatorio.artigosApresentados%TYPE;
	qtdPatrocinadores relatorio.qtdPatrocinadores%TYPE;
	valorTotalGanho relatorio.valorTotalGanho%TYPE;
	valorTotalGasto relatorio.valorTotalGasto%TYPE;
	
	-- Variáveis auxiliares
	valorPat NUMBER(10, 2);
	despesas NUMBER(10, 2);
	auxilios NUMBER(10, 2);
	
	-- Cursor para os eventos
	CURSOR cursor_eventos IS
		SELECT ev.codEv, ev.nomeEv
		FROM evento ev;
	
	-- Cursor para as edições
	CURSOR cursor_edicoes (in_codEv NUMBER) IS
		SELECT ed.numEd, ed.taxaEd
		FROM edicao ed
		WHERE ed.codEv = in_codEv;
	
	-- Cursores
	var_cursor_eventos cursor_eventos%ROWTYPE;
	var_cursor_edicoes cursor_edicoes%ROWTYPE;
	
	-- Exceptions
	cursor_eventos_exception EXCEPTION;
	cursor_edicoes_exception EXCEPTION;
	evento_not_found EXCEPTION;
	edicao_not_found EXCEPTION;
BEGIN
	
	-- Remove os dados inseridos no relatório
	DELETE FROM relatorio;

	-- Cursor dos eventos
	IF cursor_eventos%ISOPEN THEN 
		RAISE cursor_eventos_exception; -- cursor já aberto
	ELSE	
		OPEN cursor_eventos;
	END IF;

	-- Busca os eventos
	LOOP
	
		FETCH cursor_eventos INTO var_cursor_eventos;
		
		-- Nenhum evento encontrado
		IF(var_cursor_eventos.codEv IS NULL) THEN
			RAISE evento_not_found;
		END IF;
		
		EXIT WHEN cursor_eventos%NOTFOUND;
		
		-- Cursor das edições
		IF cursor_edicoes%ISOPEN THEN 
			RAISE cursor_edicoes_exception; -- cursor já aberto
		ELSE
			OPEN cursor_edicoes(var_cursor_eventos.codEv);
		END IF;

			-- Busca as edições do evento
			LOOP

				FETCH cursor_edicoes INTO var_cursor_edicoes;
				
				-- Nenhuma edição encontrada
				IF(var_cursor_edicoes.numEd IS NULL) THEN
					RAISE edicao_not_found;
				END IF;
				
				EXIT WHEN cursor_edicoes%NOTFOUND;
				
				-- Conta o número de inscritos na edição do evento
				SELECT COUNT(i.idPart)
					INTO inscritos
					FROM inscrito i
					WHERE i.codEv = var_cursor_eventos.codEv 
						AND i.numEd = var_cursor_edicoes.numEd;
				
				-- Conta o número de artigos apresentados na edição do evento		
				SELECT COUNT(art.idArt)
					INTO artigosApresentados
					FROM artigo art 
						JOIN inscrito i ON (art.codEv = i.codEv AND art.numEd = i.numEd AND art.idApr = i.idPart)
					WHERE art.codEv = var_cursor_eventos.codEv 
						AND art.numEd = var_cursor_edicoes.numEd;
						
				-- Conta o número de patrocinadores e seus valores da edição do evento
				SELECT COUNT(pat.cnpjPat), SUM(pat.valorPat)
					INTO qtdPatrocinadores, valorPat
					FROM patrocinio pat 
					WHERE pat.codEv = var_cursor_eventos.codEv 
						AND pat.numEd = var_cursor_edicoes.numEd;
				
				-- Calcula o valor total ganho (valor ganho com os patrocínios + valor ganho com as inscrições)
				valorTotalGanho := valorPat + (inscritos * var_cursor_edicoes.taxaEd);
				
				-- Conta o valor gasto com despesas na edição do evento
				SELECT SUM(d.valorDesp)
					INTO despesas
					FROM despesa d
					WHERE d.codEv = var_cursor_eventos.codEv 
						AND d.numEd = var_cursor_edicoes.numEd;
						
				-- Conta o valor gasto com auxílios na edição do evento
				SELECT SUM(aux.valorAux)
					INTO auxilios
					FROM auxilio aux
						JOIN patrocinio pat ON (aux.cnpjPat = pat.cnpjPat AND aux.codEvPat = pat.codEv AND aux.numEdPat = pat.numEd)
					WHERE aux.codEvPat = var_cursor_eventos.codEv 
						AND aux.numEdPat = var_cursor_edicoes.numEd;
				
				-- Calcula o valor total gasto (valor gasto com as despesas + valor gasto com os auxílios)
				valorTotalGasto := despesas + auxilios;
						
				-- Insere na tabela relatorio
				INSERT INTO relatorio VALUES
				(
					var_cursor_eventos.codEv,
					var_cursor_eventos.nomeEv,
					var_cursor_edicoes.numEd,
					inscritos,
					artigosApresentados,
					qtdPatrocinadores,
					valorTotalGanho,
					valorTotalGasto,
					valorTotalGanho - valorTotalGasto
				);
				
			END LOOP;
		
		CLOSE cursor_edicoes;
	
	END LOOP;

	CLOSE cursor_eventos;
	
	EXCEPTION
	
		-- Exceptions definidas
		WHEN cursor_eventos_exception
			THEN DBMS_OUTPUT.PUT_LINE('cursor_eventos já está aberto.');
		WHEN cursor_edicoes_exception
			THEN DBMS_OUTPUT.PUT_LINE('cursor_edicoes já está aberto.');
		WHEN evento_not_found
			THEN DBMS_OUTPUT.PUT_LINE('Nenhum evento encontrado.');
		WHEN edicao_not_found
			THEN DBMS_OUTPUT.PUT_LINE('Nenhuma edição encontrada.');
		WHEN NO_DATA_FOUND
			THEN DBMS_OUTPUT.PUT_LINE('SELECT INTO não retornou nenhuma tupla.');
		WHEN TOO_MANY_ROWS
			THEN DBMS_OUTPUT.PUT_LINE('SELECT INTO retornou mais de uma tupla.');
		WHEN INVALID_CURSOR
			THEN DBMS_OUTPUT.PUT_LINE('Operação inválida com o cursor.');
		WHEN PROGRAM_ERROR
			THEN DBMS_OUTPUT.PUT_LINE('Ocorreu erro interno do PL/SQL.');
		WHEN ROWTYPE_MISMATCH
			THEN DBMS_OUTPUT.PUT_LINE('Tipo do cursor e da variável incompatíveis.');
		WHEN STORAGE_ERROR
			THEN DBMS_OUTPUT.PUT_LINE('Memória insuficiente para a operação.');
		WHEN OTHERS
			THEN DBMS_OUTPUT.PUT_LINE('Houve um erro durante a execução.');
			
END relatorio_eventos;
/
/
/**
 *
 * SCRIPT DE INSERÇÃO
 *
 */
/
-- Delete em todas as tabelas
DELETE FROM evento;
DELETE FROM edicao;
DELETE FROM pessoa;
DELETE FROM inscrito;
DELETE FROM artigo;
DELETE FROM escreve;
DELETE FROM organiza;
DELETE FROM patrocinador;
DELETE FROM patrocinio;
DELETE FROM despesa;
DELETE FROM auxilio;
/
/
/**
 * Inserção na tabela Evento
 * @codEv							código do evento
 * @nomeEv							nome do evento
 * @descricaoEv						descrição do evento
 * @websiteEv						endereço do website do evento
 * @totalArtigosApresentadosEv		número de artigos apresentados no evento (atributo derivado)
 */
INSERT INTO evento VALUES(seq_evento.NEXTVAL, 'Simpósio Brasileiro de Redes de Computadores e Sistemas Distribuídos', 'O Simpósio Brasileiro de Redes de Computadores e Sistemas Distribuídos (SBRC) é um evento anual promovido pela Sociedade Brasileira de Computação (SBC) e pelo Laboratório Nacional de Redes de Computadores (LARC). Ao longo dos anos, o SBRC tornou-se o mais importante evento científico nacional em redes de computadores e sistemas distribuídos do país, e um dos mais concorridos em Informática', 'sbrc2011.facom.ufms.br', 0);
/
/
/**
 * Inserção na tabela Edicao
 * @codEv 						código do evento
 * @numEd						número da edição do evento
 * @descricaoEd					descrição da edição do evento
 * @dataInicioEd				data de início da edição do evento
 * @dataFimEd					data de término da edição do evento
 * @localEd						local de realização da edição do evento
 * @taxaEd						taxa de inscrição na edição do evento
 * @saldoFinanceiroEd			saldo financeiro da edição do evento (atributo derivado)
 * @qtdArtigosApresentadosEd	quantidade de artigos apresentados na edição do evento (atributo derivado)
 */
/
-- Edição de 2011 (XXIV) 
INSERT INTO edicao VALUES(1, 29, 'A vigésima nona edição do SBRC será realizada pela primeira vez na região Centro Oeste na cidade de Campo Grande, MS, de 30 de maio a 3 de junho de 2011, sob responsabilidade da Faculdade de Computação da Universidade Federal de Mato Grosso do Sul (UFMS).  O SBRC 2011 proporcionará uma rica variedade de atividades, tais como sessões técnicas, minicursos, painéis e debates, workshops, salão de ferramentas, palestras e tutoriais proferidos por convidados de renome internacional', to_date('30/05/2011', 'dd/mm/yyyy'), to_date('03/06/2011', 'dd/mm/yyyy'), 'Campo Grande-MS', 100.00, 0.00, 0);
/
-- Edição de 2012 (XXX)
INSERT INTO edicao VALUES(1, 30, 'Em sua edição 30, o simpósio será realizado a partir de 30 abril - 4 maio 2012, no Centro de Artes e Convenções da UFOP de (Parque Metalurgico Augusto Barbosa), na cidade de Ouro Preto, Minas Gerais, Brasil. O evento está sendo organizado pelo Departamento de Ciência da Computação da Universidade Federal de Minas Gerais (UFMG), com o apoio do Departamento de Computação da Universidade Federal de Ouro Preto (UFOP)', to_date('30/04/2012', 'dd/mm/yyyy'), to_date('04/05/2012', 'dd/mm/yyyy'), 'Ouro Preto-MG', 125.00, 0.00, 0);
/
-- Edição de 2013 (XXXI)
INSERT INTO edicao VALUES(1, 31, 'Em sua 31ª edição, o Simpósio será realizado de 6 a 10 de maio de 2013 em Brasília, DF. A realização do evento está sob a responsabilidade conjunta dos Departamentos de Ciência da Computação (CIC) e Engenharia Elétrica (ENE) da Universidade de Brasília (UnB). O evento será composto de sessões técnicas, minicursos, painéis e debates, workshops, salão de ferramentas, palestras e tutoriais proferidos por convidados de renome internacional', to_date('06/05/2013', 'dd/mm/yyyy'), to_date('10/05/2013', 'dd/mm/yyyy'), 'Brasília-DF', 140.00, 0.00, 0);
/
/
/**
 * Inserção na tabela Pessoa
 * @idPe					identificador da pessoa
 * @nomePe					nome da pessoa
 * @emailPe					email da pessoa
 * @instituicaoPe			instituição associada à pessoa
 * @telefonePe				número do telefone da pessoa
 * @nacionalidadePe			nacionalidade da pessoa
 * @enderecoPe				endereço da pessoa
 * @tipoOrganizador			armazena se a pessoa é organizadora ou não
 * @tipoParticipante		armazena se a pessoa é participante ou não
 * @tipoAutor				armazena se a pessoa é autora ou não
 */
/
-- Pessoas da edição de 2011
/
-- Organizadores (id's 1-8)
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Ronaldo Alves Ferreira', 'ronaldo@ufms.br', 'UFMS', '(15) 3826-1240', 'Brasileiro', 'Rua Comendador, 1250', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Artur Ziviani', 'ziviani@lncc.br', 'LNCC', '(12) 3856-3085', 'Brasileiro', 'Rua Farias Lima, 324', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Bruno Schulze', 'schulze@lncc.br', 'LNCC', '(11) 3826-2369', 'Brasileiro', 'Rua Golias, 106', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Nelson Luis Saldanha da Fonseca', 'nelsons@unicamp.br', 'UNICAMP', '(34) 3884-3467', 'Brasileiro', 'Rua Pitágoras, 589', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'José Augusto Suruagy Monteiro', 'suruagy@unifacs.br', 'UNIFACS', '(19) 3834-2222', 'Brasileiro', 'Av Levi, 666', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Fabíola Gonçalves Pereira Greve', 'fabiola@ufba.br', 'UFBA', '(45) 3838-1515', 'Brasileiro', 'Rua Liberdade, 1340', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Fábio Moreira Costa', 'fabiom@ufg.br', 'UFMS', '(18) 3812-1233', 'Brasileiro', 'Rua Gardênia, 98', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luis Carlos Erpen de Bona', 'erpen@ufpr.br', 'UFPR', '(11) 3888-2416', 'Brasileiro', 'Av Brasil, 4567', 'S', 'N', 'N');
/
-- Autores/Participantes (id's 9-39)
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Pedro Gomes', 'pedrog@unicamp.br', 'UNICAMP', '(12) 3234-5555', 'Brasileiro', 'Rua Alves, 14', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Nelson Fonseca', 'nelsonf@unicamp.br', 'UNICAMP', '(67) 3816-3040', 'Brasileiro', 'Rua Erundina, 367', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rudolfo Runcos', 'rudolfo@ufpr.br', 'UFPR', '(14) 3225-2565', 'Brasileiro', 'Rua Sales, 67', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Eduardo Parente Ribeiro', 'eduardoparente@ufpr.br', 'UFPR', '(22) 3888-3241', 'Brasileiro', 'Rua Miguel Alberto, 777', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rafael Pasquini', 'rafapasq@unicamp.br', 'UNICAMP', '(45) 3222-5645', 'Brasileiro', 'Rua Sardinha, 900', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Fábio Luciano Verdi', 'fabioluciano@ufscar.br', 'UFSCar', '(34) 3221-9875', 'Brasileiro', 'Av Fagundes Oliveira, 16', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Carlos Froldi', 'carlosf@unicamp.br', 'UNICAMP', '(68) 3345-5743', 'Brasileiro', 'Rua Smith, 475', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Carlos Papotti', 'carlosp@unisal.br', 'Unisal', '(19) 3223-5903', 'Brasileiro', 'Rua Amorim, 354', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Daniel Manzato', 'manzato@unicamp.br', 'UNICAMP', '(11) 3243-5214', 'Brasileiro', 'Rua Esmeralda, 30', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Gustavo Alkmim', 'alkmim@unicamp.br', 'UNICAMP', '(16) 3224-1214', 'Brasileiro', 'Av das Palmeiras, 3478', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Daniel Batista', 'danbatista@usp.br', 'USP', '(12) 3214-1515', 'Brasileiro', 'Rua Legendre, 219', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rostand Costa', 'rostand@ufpb.br', 'UFPB', '(13) 3227-4335', 'Brasileiro', 'Rua da Saudade, 9999', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Francisco Brasileiro', 'chicob@ufcg.br', 'UFCG', '(19) 3225-7125', 'Brasileiro', 'Rua Tarcísio, 4698', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Guido Lemos Filho', 'guido@ufpb.br', 'UFPB', '(44) 3125-4433', 'Brasileiro', 'Rua Anibal, 654', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Sushanta Karmakar', 'sushanta@iitg.in', 'Indian Institute of Technology Guwahati', '(35) 3111-6789', 'Indiano', 'Rua Everaldo, 123', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Alexandre Pires', 'xandep@ufrj.br', 'UFRJ', '(65) 3145-2157', 'Brasileiro', 'Av das Andorinhas, 3456', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'José Ferreira 	de Rezende', 'jfrezende@ufrj.br', 'UFRJ', '(47) 3145-7896', 'Brasileiro', 'Av Mangaçu, 214', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Leandro Villas', 'leandrovillas@ufmg.br', 'UFMG', '(11) 3156-4355', 'Brasileiro', 'Rua Tamboríu, 368', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Azzedine Boukerche', 'azzedine@site.com', 'University of Ottawa', '(12) 3126-1259', 'Americano', 'Rua Pitanguaçu, 256', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Daniel Guidoni', 'guidoni@ufmg.br', 'UFMG', '(14) 3826-2015', 'Brasileiro', 'Rua Tajubá, 389', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Marcio Moreno', 'marciomoreno@puc-rio.br', 'PUC-Rio', '(20) 3826-3400', 'Brasileiro', 'Rua Guiomar, 2678', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luiz Fernando Soares', 'luizs@puc-rio.br', 'PUC-Rio', '(11) 3826-9087', 'Brasileiro', 'Rua Mercadante, 1457', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Marcelo Nascimento', 'marcelon@cpqd.br', 'CPqD', '(13) 3826-2222', 'Brasileiro', 'Rua Maresias, 234', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Christian Esteve Rothenberg', 'christian@cpqd.br', 'CPqD', '(16) 3826-2435', 'Brasileiro', 'Av Panguaçu, 555', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Thiago Borges de Oliveira', 'thiagob@ufg.br', 'UFG', '(45) 3846-2567', 'Brasileiro', 'Rua Peristal, 478', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Vagner Sacramento', 'vagner@ufg.br', 'UFG', '(11) 3846-5609', 'Brasileiro', 'Rua das Flores, 470', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Erico Lemos', 'erico@ufrj.br', 'UFRJ', '(41) 3826-3467', 'Brasileiro', 'Rua Deutch, 345', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luci Pirmez', 'luci@ufrj.br', 'UFRJ', '(65) 3846-9876', 'Brasileiro', 'Rua Seringueira, 6311', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Igor Santos', 'igors@ufrj.br', 'UFRJ', '(11) 3832-1256', 'Brasileiro', 'Rua da Araucária, 5412', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'André Drummond', 'drummond@unicamp.br', 'UNICAMP', '(19) 3845-3409', 'Brasileiro', 'Rua das Galinhas, 752', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Xiaomin Chen', 'xiaomin@tucwb.ch', 'Technical University Carolo-Wilhelmina of Braunschweig', '(12) 3812-2020', 'Chinês', 'Rua Virundu, 634', 'N', 'S', 'S');
/
-- Pessoas da edição de 2012
/
-- Organizadores (id's 40-47)
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Jussara Marques de Almeida', 'jussara@ufmg.br', 'UFMG', '(14) 3458-5576', 'Brasileiro', 'Rua Sardanha, 335', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Dorgival Olavo Guedes Neto', 'dorgival@ufmg.br', 'UFMG', '(17) 9865-7631', 'Brasileiro', 'Av Celibato, 589', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Elias P. Duarte Jr.', 'eliasjr@ufpr.br', 'UFPR', '(42) 4873-4973', 'Brasileiro', 'Rua Figueiredo, 9823', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Edmundo Madeira', 'edmundom@unicamp.br', 'UNICAMP', '(98) 8723-0811', 'Brasileiro', 'Rua da Escravidão, 12', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Antônio Jorge Gomes Abelém', 'antonioj@ufpa.br', 'UFPA', '(21) 9898-7843', 'Brasileiro', 'Av Guilpert, 897', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Aldri Luiz dos Santos', 'aldri@ufpr.br', 'UFPR', '(23) 2781-9738', 'Brasileiro', 'Rua Kelpher, 98', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Ricardo Augusto Rabelo Oliveira', 'ricardorabelo@ufop.br', 'UFOP', '(56) 7831-9738', 'Brasileiro', 'Rua dos Mexilhões, 9211', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Humberto Torres Marques Neto', 'humbertoneto@puc-minas.br', 'PUC-Minas', '(84) 6783-9783', 'Brasileiro', 'Rua Vila Lobos, 392', 'S', 'N', 'N');
/
-- Autores/Participantes (id's 48-73)
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Daniel Menasche', 'menasche@ufrj.br', 'UFRJ', '(15) 8721-8468', 'Brasileiro', 'Rua Javali, 42', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Antonio Rocha', 'rocha@uff.br', 'UFF', '(34) 8873-9273', 'Brasileiro', 'Rua Faizão, 6542', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rodolfo Villaça', 'villaca@uec.br', 'Universidade Estadual de Campinas', '(19) 7726-8621', 'Brasileiro', 'Av dos Amores, 9832', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luciano de Paula', 'luciano@ifsp.br', 'IFSP', '(23) 3120-2489', 'Brasileiro', 'Rua das Vidraças, 543', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Max do Val Machado', 'maxmachado@ufmg.br', 'UFMG', '(54) 2345-5452', 'Brasileiro', 'Rua Ermelindo, 9789', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Raquel Mini', 'mini@puc-minas.br', 'PUC-Minas', '(41) 4340-0840', 'Brasileiro', 'Rua Florenço, 245', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Paulo Floriano', 'floriano@usp.br', 'USP', '(11) 9893-9829', 'Brasileiro', 'Av Carlitos, 5323', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luciana Arantes', 'arantes@upvi.fr', 'Université de Paris VI', '(33) 6573-2124', 'Francesa', 'Rua Vaz Souza, 89', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Jeferson Stênico', 'stenico@uec.br', 'Universidade Estadual de Campinas', '(19) 6842-9784', 'Brasileiro', 'Rua Norte Sul, 4349', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Lee Ling', 'lee@unicamp.br', 'UNICAMP', '(19) 8783-9792', 'Chinês', 'Rua Tulio, 5432', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Marcelo Vasconcelos', 'vasconcelos@ime.br', 'IME', '(11) 4231-3103', 'Brasileiro', 'Rua Prestes, 1323', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Ronaldo Salles', 'salles@ime.br', 'IME', '(11) 9234-0932', 'Brasileiro', 'Av Armerindo, 53', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Jorge Castro e Silva', 'jorgecastro@uece.br', 'UECE', '(89) 9893-9731', 'Brasileiro', 'Rua das Orquídeas, 111', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'José Everardo Bessa Maia', 'everardo@uece.br', 'UECE', '(89) 9084-9482', 'Brasileiro', 'Av Imigrante, 333', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Guilherme Maia', 'maia@ufmg.br', 'Universidade Federal de Minas Gerais', '(35) 4234-1245', 'Brasileiro', 'Rua Dr. Runco, 594', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Aline Viana', 'viana@is.fr', 'Inria Saclay - Ile de France', '(33) 3124-0905', 'Francesa', 'Rua da Boiada, 6435', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Alirio Sá', 'alirio@ufba.br', 'UFBA', '(66) 4234-4340', 'Brasileiro', 'Rua das Amoras, 806', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'João Paulo Nascimento', 'joao_paulo@cefet.br', 'CEFET-MG', '(11) 4230-0042', 'Brasileiro', 'Rua dos Canaviais, 5042', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Thiago Genez', 'genez@unicamp.br', 'UNICAMP', '(44) 3198-4080', 'Brasileiro', 'Av dos Anús, 222', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luiz Fernando Bittencourt', 'bittencourt@unicamp.br', 'UNICAMP', '(19) 3392-0940', 'Brasileiro', 'Rua das Hortaliças, 303', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Alisson Pontes', 'alisson@unicamp.br', 'UNICAMP', '(19) 3302-2435', 'Brasileiro', 'Av Everaldo Salles, 3982', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Tiago Andrade', 'tandrade@unicamp.br', 'UNICAMP', '(19) 3330-0489', 'Brasileiro', 'Rua dos Cupins, 893', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Omar Branquinho', 'branquinho@puc.br', 'PUC', '(19) 3989-0820', 'Brasileiro', 'Rua Humberto, 444', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Esteban Rodriguez', 'esteban@unicamp.br', 'UNICAMP', '(19) 3392-9842', 'Brasileiro', 'Rua da Tribo, 9832', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Diogo Henrique Bezerra', 'bezerra@ufpb.br', 'Universidade Federal da Paraíba', '(65) 9893-9724', 'Brasileiro', 'Rua do Milharal, 309', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Dênio Mariz Sousa', 'denio@ifpb.br', 'IFPB', '(43) 5382-0803', 'Brasileiro', 'Rua da Uva, 972', 'N', 'S', 'S');
/
-- Pessoas da edição de 2013
/
-- Organizadores (id's 74-81)
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Jacir Luiz Bordim', 'jacir@unb.br', 'UnB', '(45) 8482-2387', 'Brasileiro', 'Rua do Planalto, 4739', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rafael Timóteo de Sousa Júnior', 'rafatimoteo@unb.br', 'UnB', '(45) 9472-4874', 'Brasileiro', 'Av Brigadeiro, 7438', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'William Ferreira Giozza', 'giozza@unb.br', 'UnB', '(45) 8438-2487', 'Brasileiro', 'Av Sargento, 9472', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Carlos André Guimarães Ferraz', 'carlosf@ufpe.br', 'UFPE', '(68) 4927-9341', 'Brasileiro', 'Rua Gregório Alves, 8392', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Lisandro Zambenedetti Granville', 'zambenedetti@ufrgs.br', 'UFRGS', '(34) 4723-2482', 'Brasileiro', 'Rua da Amargura, 42', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Joni da Silva Fraga', 'joni@ufsc.br', 'UFSC', '(13) 3312-1397', 'Brasileiro', 'Rua dos Siris, 497', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Edson Moreira Santos', 'edson@usp.br', 'USP', '(16) 3383-5792', 'Brasileiro', 'Av Trabalhador São Carlense, 892', 'S', 'N', 'N');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Gustavo Sousa Pavani', 'gupavani@ufabc.br', 'UFABC', '(11) 4793-2498', 'Brasileiro', 'Rua Miguel Araiz, 312', 'S', 'N', 'N');
/
-- Autores/Participantes (id's 82-111)
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Leonardo Oliveira', 'leoliveira@ufmg.br', 'UFMG', '(21) 3435-4356', 'Brasileiro', 'Rua da Filadélfia, 424', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Italo Cunha', 'italo@ufmg.br', 'UFMG', '(21) 3642-3546', 'Brasileiro', 'Rua de Santos, 900', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Felipe Domingos da Cunha', 'fecunha@ufmg.br', 'UFMG', '(21) 9743-4762', 'Brasileiro', 'Rua do Porto, 972', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Geraldo Pereira', 'geraldo@usp.br', 'USP', '(16) 8792-2479', 'Brasileiro', 'Av Miguel Petroni, 9800', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Jó Ueyama', 'ueyama@usp.br', 'USP', '(16) 8792-9539', 'Brasileiro', 'Rua de Pedra, 49', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Denis Rosário', 'denis@ufpa.br', 'UFPA', '(34) 4241-1425', 'Brasileiro', 'Av Humberto Martins, 9002', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rodrigo Costa', 'rodrigo@ufpa.br', 'UFPA', '(34) 5321-4352', 'Brasileiro', 'Av dos Cascalhos, 4222', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Alysson Santos', 'alysson@ufpe.br', 'UFPE', '(69) 5542-3546', 'Brasileiro', 'Rua Baião de Dois, 3533', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Petronio Júnior', 'petronio@ufpe.br', 'UFPE', '(69) 5254-5420', 'Brasileiro', 'Rua de Terra, 9471', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Stenio Fernandes', 'stenio@ufpe.br', 'UFPE', '(69) 5390-0029', 'Brasileiro', 'Rua das Briófitas, 9004', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Brivaldo Junior', 'brivaldo@ufms.br', 'UFMS', '(35) 3395-4230', 'Brasileiro', 'Rua da Brigada, 333', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Marcelo Caggiani Luizelli', 'caggiani@ufrgs.br', 'UFRGS', '(45) 3872-4878', 'Brasileiro', 'Av das Cocadas, 444', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Leonardo Bays', 'bays@ufrgs.br', 'UFRGS', '(45) 3981-4287', 'Brasileiro', 'Rua das Alianças, 99', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Luciana Buriol', 'buriol@ufrgs.br', 'UFRGS', '(33) 9791-0910', 'Brasileiro', 'Rua dos Palmeiras, 567', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Marcelo Ribeiro Xavier da Silva', 'xavier@ufsc.br', 'UFSC', '(33) 8757-9842', 'Brasileiro', 'Rua das Cactáceas, 11', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Lau Cheuk Lung', 'lau@ufsc.br', 'UFSC', '(33) 9711-1309', 'Chinês', 'Rua do Café, 245', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Aldelir Fernando Luiz', 'aldelir@ufsc.br', 'UFSC', '(33) 9792-0380', 'Brasileiro', 'Rua Floriano Peixoto, 109', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Rômerson Oliveira', 'romerson@ufu.br', 'UFU', '(25) 9782-4892', 'Brasileiro', 'Rua do Sol, 390', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Daniel Mesquita', 'mesquita@ufu.br', 'UFU', '(22) 8701-3879', 'Brasileiro', 'Rua dos Planetas, 9930', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Iallen Sousa', 'iallen@ufpi.br', 'UFPI', '(44) 3584-0900', 'Brasileiro', 'Rua das Insignéas, 202', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Gilvan Durães', 'gilvan@ufba.br', 'UFBA', '(43) 9294-0222', 'Brasileiro', 'Rua das Nacionalidades, 2224', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Guilherme Domingues', 'guidom@ufrj.br', 'UFRJ', '(21) 4293-1345', 'Brasileiro', 'Rua de Copacabana, 342', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Edmundo de Souza e Silva', 'edmundo@ufrj.br', 'UFRJ', '(21) 4421-1345', 'Brasileiro', 'Rua dos Elefantes, 115', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Darryl Veitch', 'darryl@uom.au', 'University of Melbourne', '(66) 6266-4256', 'Australiano', 'Av das Parábolas, 351', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Christophe Diot', 'diot@tech.fr', 'Technicolor', '(78) 6352-2566', 'Francês', 'Rua do Comendador, 449', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Fernando Garcia', 'garcia@ifce.br', 'IFCE', '(25) 5294-2450', 'Brasileiro', 'Rua Iracema, 5201', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'José Neuman de Souza', 'neuman@ufc.br', 'UFC', '(95) 5939-5920', 'Brasileiro', 'Rua Queirós, 588', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Alex Ramos', 'alex@unifor.br', 'UNIFOR', '(59) 4265-5502', 'Brasileiro', 'Rua dos Girassóis, 146', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Raimir Holanda', 'raimir@unifor.br', 'UNIFOR', '(43) 5255-5632', 'Brasileiro', 'Av Azul, 462', 'N', 'S', 'S');
INSERT INTO pessoa VALUES(seq_pessoa.NEXTVAL, 'Celso Barbosa Carvalho', 'celso@ufam.br', 'UFAM', '(54) 5201-2500', 'Brasileiro', 'Av das Formigas, 9503', 'N', 'S', 'S');
/
/
/**
 * Inserção na tabela inscrito
 * @codEv						código do evento
 * @numEd 						número da edição do evento
 * @idPart						identificador do participante (pessoa)				
 * @dataInsc					data de realização da inscrição
 * @tipoApresentador			armazena se o inscrito irá apresentar ou não
 */
/
-- Edição de 2011
INSERT INTO inscrito VALUES(1,29, 9, to_date('05/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 10, to_date('19/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 11, to_date('07/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 12, to_date('12/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 13, to_date('09/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 14, to_date('15/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 15, to_date('06/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 16, to_date('13/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 17, to_date('17/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 18, to_date('05/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 19, to_date('18/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 20, to_date('11/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 21, to_date('06/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 22, to_date('10/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 23, to_date('07/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 24, to_date('12/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 25, to_date('14/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 26, to_date('19/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 27, to_date('17/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 28, to_date('05/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 29, to_date('12/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 30, to_date('08/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 31, to_date('13/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 32, to_date('09/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 33, to_date('17/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 34, to_date('05/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 35, to_date('07/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 36, to_date('12/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 37, to_date('09/05/2011', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,29, 38, to_date('16/05/2011', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,29, 39, to_date('14/05/2011', 'dd/mm/yyyy'), 'N');
/
-- Edição de 2012
-- CORREÇÃO DO T1: Adição de 4 inscritos nesta edição
INSERT INTO inscrito VALUES(1,30, 9, to_date('03/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 10, to_date('06/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 82, to_date('10/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 83, to_date('04/04/2012', 'dd/mm/yyyy'), 'N');
-- Inscritos originais do t1
INSERT INTO inscrito VALUES(1,30, 48, to_date('01/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 49, to_date('15/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 50, to_date('03/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 51, to_date('08/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 52, to_date('10/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 53, to_date('11/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 54, to_date('02/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 55, to_date('04/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 56, to_date('15/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 57, to_date('01/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 58, to_date('07/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 59, to_date('12/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 60, to_date('03/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 61, to_date('15/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 62, to_date('01/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 63, to_date('08/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 64, to_date('03/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 65, to_date('02/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 66, to_date('13/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 67, to_date('11/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 68, to_date('04/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 69, to_date('11/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 70, to_date('01/04/2012', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,30, 71, to_date('03/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 72, to_date('12/04/2012', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,30, 73, to_date('07/04/2012', 'dd/mm/yyyy'), 'N');
/
-- Edição de 2013
INSERT INTO inscrito VALUES(1,31, 82, to_date('15/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 83, to_date('20/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 84, to_date('18/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 85, to_date('30/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 86, to_date('25/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 87, to_date('22/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 88, to_date('16/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 89, to_date('21/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 90, to_date('28/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 91, to_date('24/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 92, to_date('19/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 93, to_date('17/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 94, to_date('22/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 95, to_date('23/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 96, to_date('30/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 97, to_date('17/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 98, to_date('16/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 99, to_date('26/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 100, to_date('15/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 101, to_date('27/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 102, to_date('30/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 103, to_date('18/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 104, to_date('15/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 105, to_date('29/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 106, to_date('22/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 107, to_date('17/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 108, to_date('26/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 109, to_date('25/04/2013', 'dd/mm/yyyy'), 'S');
INSERT INTO inscrito VALUES(1,31, 110, to_date('20/04/2013', 'dd/mm/yyyy'), 'N');
INSERT INTO inscrito VALUES(1,31, 111, to_date('15/04/2013', 'dd/mm/yyyy'), 'S');
/
/
/**
 * Inserção na tabela artigo
 * @idArt					identificador do artigo
 * @tituloArt				título do artigo
 * @dataApresArt			data da apresentação do artigo
 * @horaApresArt			hora da apresentação do artigo
 * @codEv					código do evento
 * @numEd					número da edição do evento
 * @idApr					identificador do apresentador (pessoa)
 */
/
-- Edição de 2011
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Otimização Bicritério de Recursos para Redes Sem Fio de Acesso e Rádio sobre Fibra', to_date('02/05/2011', 'dd/mm/yyyy'), to_date('02/05/2011 08:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 9);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Avaliação de Parâmetros do SCTP para Transporte de Tráfego VoIP em Cenários com Perdas', to_date('30/05/2011', 'dd/mm/yyyy'), to_date('30/05/2011 10:30', 'dd/mm/yyyy hh24:mi'), 1, 29, 11);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Integrating Servers and Networking using an XOR-based Flat Routing Mechanism in 3-cube Server-centric Data Centers', to_date('01/05/2011', 'dd/mm/yyyy'), to_date('01/05/2011 14:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 13);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Fast DCCP: Uma Variante do Protocolo DCCP para Redes de Alta Velocidade', to_date('31/05/2011', 'dd/mm/yyyy'), to_date('31/05/2011 09:40', 'dd/mm/yyyy hh24:mi'), 1, 29, 15);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Esquemas para Troca Rápida de Canais em Sistemas IPTV', to_date('01/05/2011', 'dd/mm/yyyy'), to_date('01/05/2011 15:20', 'dd/mm/yyyy hh24:mi'), 1, 29, 17);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Mapeamento de Redes Virtuais em Substratos de Rede', to_date('30/05/2011', 'dd/mm/yyyy'), to_date('30/05/2011 13:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 18);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Sobre a Amplitude da Elasticidade dos Provedores Atuais de Computação na Nuvem', to_date('02/05/2011', 'dd/mm/yyyy'), to_date('02/05/2011 10:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 20);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Adaptive Convergecast by Distributed Topology Switching', to_date('01/05/2011', 'dd/mm/yyyy'), to_date('01/05/2011 09:50', 'dd/mm/yyyy hh24:mi'), 1, 29, 23);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Aumento do Reuso Espacial em Redes Ad Hoc IEEE 802.11 com o uso de Enlaces Independentes', to_date('03/05/2011', 'dd/mm/yyyy'), to_date('03/05/2011 16:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 24);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Explorando a Correlação Espacial na Coleta de Dados em Redes de Sensores sem Fio', to_date('02/05/2011', 'dd/mm/yyyy'), to_date('02/05/2011 11:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 26);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Uma Arquitetura Orientada a Componentes para o Ginga', to_date('01/05/2011', 'dd/mm/yyyy'), to_date('01/05/2011 17:00', 'dd/mm/yyyy hh24:mi'), 1, 29, 29);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'RouteFlow: Roteamento Commodity Sobre Redes Programáveis', to_date('03/05/2011', 'dd/mm/yyyy'), to_date('03/05/2011 08:30', 'dd/mm/yyyy hh24:mi'), 1, 29, 31);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'DSI-RTree - Um Índice R-Tree Escalável Distribuído', to_date('30/05/2011', 'dd/mm/yyyy'), to_date('30/05/2011 14:45', 'dd/mm/yyyy hh24:mi'), 1, 29, 33);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Algoritmo Distribuído para Detecção de Dano em Aero Geradores Utilizando Redes de Atuadores e Sensores Sem Fio', to_date('01/05/2011', 'dd/mm/yyyy'), to_date('01/05/2011 10:15', 'dd/mm/yyyy hh24:mi'), 1, 29, 35);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Roteamento Multicaminho para Provisão Eficiente de Recursos Interdomínio com Qualidade de Serviço em Redes Ópticas WDM', to_date('31/05/2011', 'dd/mm/yyyy'), to_date('31/05/2011 16:30', 'dd/mm/yyyy hh24:mi'), 1, 29, 38);
/
-- Edição de 2012
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Stability of Peer-to-Peer Swarming Systems', to_date('30/04/2012', 'dd/mm/yyyy'), to_date('30/04/2012 09:00', 'dd/mm/yyyy hh24:mi'), 1, 30, 48);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Hamming DHT: An Indexing System for Similarity Search', to_date('01/05/2012', 'dd/mm/yyyy'), to_date('01/05/2012 10:15', 'dd/mm/yyyy hh24:mi'), 1, 30, 50);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Política Híbrida para Determinar o Momento Acordado do Próximo Nó na Comunicação em Redes de Sensores Sem Fio', to_date('30/04/2012', 'dd/mm/yyyy'), to_date('30/04/2012 13:20', 'dd/mm/yyyy hh24:mi'), 1, 30, 52);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Condições de conectividade de algoritmos de exclusão mútua em redes dinâmicas', to_date('02/05/2012', 'dd/mm/yyyy'), to_date('02/05/2012 11:00', 'dd/mm/yyyy hh24:mi'), 1, 30, 54);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Accurate Second-Order Moment Multifractal Traffic Modelling', to_date('03/05/2012', 'dd/mm/yyyy'), to_date('03/05/2012 08:30', 'dd/mm/yyyy hh24:mi'), 1, 30, 56);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Emprego de Resiliência na Gerência de Redes de Computadores', to_date('30/04/2012', 'dd/mm/yyyy'), to_date('30/04/2012 14:25', 'dd/mm/yyyy hh24:mi'), 1, 30, 58);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Identifying Attacks on Computer Networks using Classifiers Committee', to_date('01/05/2012', 'dd/mm/yyyy'), to_date('01/05/2012 16:00', 'dd/mm/yyyy hh24:mi'), 1, 30, 60);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Um Protocolo de Distribuição de Dados para Redes de Sensores Sem Fio Heterogêneas com Sink Móvel', to_date('02/05/2012', 'dd/mm/yyyy'), to_date('02/05/2012 10:30', 'dd/mm/yyyy hh24:mi'), 1, 30, 62);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Impacto de Métricas de QoS no Desempenho de Detectores Auto-gerenciáveis de Defeitos para Sistemas Distribuídos', to_date('30/04/2012', 'dd/mm/yyyy'), to_date('30/04/2012 16:45', 'dd/mm/yyyy hh24:mi'), 1, 30, 64);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Um algoritmo paralelo em Hadoop para cálculo de centralidade em grafos grandes', to_date('01/05/2012', 'dd/mm/yyyy'), to_date('01/05/2012 15:00', 'dd/mm/yyyy hh24:mi'), 1, 30, 65);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Discretização do Tempo na Utilização de Programação Linear para o Problema de Escalonamento de Workflows em Múltiplos Provedores de Nuvem', to_date('03/05/2012', 'dd/mm/yyyy'), to_date('03/05/2012 09:15', 'dd/mm/yyyy hh24:mi'), 1, 30, 66);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Aprovisionamento de Lightpath Inter-domínio baseado em PCE', to_date('04/05/2012', 'dd/mm/yyyy'), to_date('04/05/2012 11:15', 'dd/mm/yyyy hh24:mi'), 1, 30, 68);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Protocolos MAC para Integração de Redes de Sensores sem Fio baseado em Rádio-sobre-Fibra', to_date('02/05/2012', 'dd/mm/yyyy'), to_date('02/05/2012 14:40', 'dd/mm/yyyy hh24:mi'), 1, 30, 69);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Mapeamento Energeticamente Eficiente de Redes Virtuais em Substratos Físicos', to_date('04/05/2012', 'dd/mm/yyyy'), to_date('04/05/2012 16:50', 'dd/mm/yyyy hh24:mi'), 1, 30, 71);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'OddCI-Ginga: Uma Plataforma para Computação de Alta Vazão baseada em Receptores de TV Digital', to_date('30/04/2012', 'dd/mm/yyyy'), to_date('30/04/2012 17:15', 'dd/mm/yyyy hh24:mi'), 1, 30, 72);
/
-- Edição de 2013
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Nova Abordagem para Acesso ao Meio em Redes de Sensores Sem Fio', to_date('06/05/2013', 'dd/mm/yyyy'), to_date('06/05/2013 08:00', 'dd/mm/yyyy hh24:mi'), 1, 31, 82);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'NodePM: Um Sistema de Monitoramento Remoto do Consumo de Energia Elétrica via Redes de Sensores sem Fio', to_date('06/05/2013', 'dd/mm/yyyy'), to_date('06/05/2013 13:15', 'dd/mm/yyyy hh24:mi'), 1, 31, 85);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Localização 3D em Redes de Sensores Sem Fio Utilizando Veículo Aéreo não Tripulado', to_date('07/05/2013', 'dd/mm/yyyy'), to_date('07/05/2013 14:00', 'dd/mm/yyyy hh24:mi'), 1, 31, 86);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'QoE-aware Multiple Path Video Transmission for Wireless Multimedia Sensor Networks', to_date('07/05/2013', 'dd/mm/yyyy'), to_date('07/05/2013 09:15', 'dd/mm/yyyy hh24:mi'), 1, 31, 87);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Explorando o processamento paralelo na classificação de tráfego em redes de alta velocidade', to_date('07/05/2013', 'dd/mm/yyyy'), to_date('07/05/2013 10:00', 'dd/mm/yyyy hh24:mi'), 1, 31, 89);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Nemo: Procurando e Encontrando Anomalias em Aplicações Distribuídas', to_date('08/05/2013', 'dd/mm/yyyy'), to_date('08/05/2013 16:20', 'dd/mm/yyyy hh24:mi'), 1, 31, 92);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Caracterizando o Impacto de Topologias no Mapeamento de Redes Virtuais', to_date('08/05/2013', 'dd/mm/yyyy'), to_date('08/05/2013 13:00', 'dd/mm/yyyy hh24:mi'), 1, 31, 93);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Tolerância a Faltas Bizantinas Usando Registradores Distribuído e Compartilhado', to_date('08/05/2013', 'dd/mm/yyyy'), to_date('08/05/2013 08:30', 'dd/mm/yyyy hh24:mi'), 1, 31, 96);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'HARP: Um novo protocolo para alta disponibilidade implementado em FPGA', to_date('09/05/2013', 'dd/mm/yyyy'), to_date('09/05/2013 11:10', 'dd/mm/yyyy hh24:mi'), 1, 31, 99);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Um novo Algoritmo de Roteamento para a Escolha da Melhor Entre as Menores Rotas', to_date('09/05/2013', 'dd/mm/yyyy'), to_date('09/05/2013 15:15', 'dd/mm/yyyy hh24:mi'), 1, 31, 101);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Enabling Information Centric Networks through Opportunistic Search, Routing and Caching', to_date('09/05/2013', 'dd/mm/yyyy'), to_date('09/05/2013 16:50', 'dd/mm/yyyy hh24:mi'), 1, 31, 103);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'RemapRoute: Reduzindo o custo do remapeamento de mudanças de roteamento na Internet', to_date('10/05/2013', 'dd/mm/yyyy'), to_date('10/05/2013 18:00', 'dd/mm/yyyy hh24:mi'), 1, 31, 105);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Sistema de Monitoramento Passivo para RSSF – Soluções Existentes e uma Nova Proposta Energeticamente Eficiente', to_date('10/05/2013', 'dd/mm/yyyy'), to_date('10/05/2013 17:10', 'dd/mm/yyyy hh24:mi'), 1, 31, 107);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Estimando o Nível de Segurança de Dados de Redes de Sensores sem Fio', to_date('06/05/2013', 'dd/mm/yyyy'), to_date('06/05/2013 10:40', 'dd/mm/yyyy hh24:mi'), 1, 31, 109);
INSERT INTO artigo VALUES(seq_artigo.NEXTVAL, 'Mecanismo Conjunto de Atribuição de Canais e Roteamento para Redes em Malha Sem Fio de Múltiplos Rádios', to_date('10/05/2013', 'dd/mm/yyyy'), to_date('10/05/2013 15:30', 'dd/mm/yyyy hh24:mi'), 1, 31, 111);
/
/
/**
 * Inserção na tabela escreve
 * @idAut					identificador do autor (pessoa)
 * @idArt					identificador do artigo
 */
/
-- Edição de 2011
INSERT INTO escreve VALUES(9, 1);
INSERT INTO escreve VALUES(10, 1);
INSERT INTO escreve VALUES(10, 4);
INSERT INTO escreve VALUES(10, 5);
INSERT INTO escreve VALUES(10, 6);
INSERT INTO escreve VALUES(11, 2);
INSERT INTO escreve VALUES(12, 2);
INSERT INTO escreve VALUES(13, 3);
INSERT INTO escreve VALUES(14, 3);
INSERT INTO escreve VALUES(15, 4);
INSERT INTO escreve VALUES(16, 4);
INSERT INTO escreve VALUES(17, 5);
INSERT INTO escreve VALUES(18, 6);
INSERT INTO escreve VALUES(19, 6);
INSERT INTO escreve VALUES(20, 7);
INSERT INTO escreve VALUES(21, 7);
INSERT INTO escreve VALUES(22, 7);
INSERT INTO escreve VALUES(23, 8);
INSERT INTO escreve VALUES(24, 9);
INSERT INTO escreve VALUES(25, 9);
INSERT INTO escreve VALUES(26, 10);
INSERT INTO escreve VALUES(27, 10);
INSERT INTO escreve VALUES(28, 10);
INSERT INTO escreve VALUES(29, 11);
INSERT INTO escreve VALUES(30, 11);
INSERT INTO escreve VALUES(31, 12);
INSERT INTO escreve VALUES(32, 12);
INSERT INTO escreve VALUES(33, 13);
INSERT INTO escreve VALUES(34, 13);
INSERT INTO escreve VALUES(35, 14);
INSERT INTO escreve VALUES(36, 14);
INSERT INTO escreve VALUES(37, 14);
INSERT INTO escreve VALUES(38, 15);
INSERT INTO escreve VALUES(39, 15);
/
-- Edição de 2012
INSERT INTO escreve VALUES(48, 16);
INSERT INTO escreve VALUES(49, 16);
INSERT INTO escreve VALUES(50, 17);
INSERT INTO escreve VALUES(51, 17);
INSERT INTO escreve VALUES(52, 18);
INSERT INTO escreve VALUES(53, 18);
INSERT INTO escreve VALUES(54, 19);
INSERT INTO escreve VALUES(55, 19);
INSERT INTO escreve VALUES(56, 20);
INSERT INTO escreve VALUES(57, 20);
INSERT INTO escreve VALUES(58, 21);
INSERT INTO escreve VALUES(59, 21);
INSERT INTO escreve VALUES(60, 22);
INSERT INTO escreve VALUES(61, 22);
INSERT INTO escreve VALUES(10, 22);
INSERT INTO escreve VALUES(62, 23);
INSERT INTO escreve VALUES(63, 23);
INSERT INTO escreve VALUES(28, 23);
INSERT INTO escreve VALUES(64, 24);
INSERT INTO escreve VALUES(65, 25);
INSERT INTO escreve VALUES(66, 26);
INSERT INTO escreve VALUES(67, 26);
INSERT INTO escreve VALUES(68, 27);
INSERT INTO escreve VALUES(10, 27);
INSERT INTO escreve VALUES(69, 28);
INSERT INTO escreve VALUES(70, 28);
INSERT INTO escreve VALUES(10, 28);
INSERT INTO escreve VALUES(71, 29);
INSERT INTO escreve VALUES(18, 29);
INSERT INTO escreve VALUES(19, 29);
INSERT INTO escreve VALUES(10, 29);
INSERT INTO escreve VALUES(72, 30);
INSERT INTO escreve VALUES(73, 30);
INSERT INTO escreve VALUES(20, 30);
/
-- Edição de 2013
INSERT INTO escreve VALUES(82, 31);
INSERT INTO escreve VALUES(83, 31);
INSERT INTO escreve VALUES(84, 31);
INSERT INTO escreve VALUES(85, 32);
INSERT INTO escreve VALUES(86, 32);
INSERT INTO escreve VALUES(26, 32);
INSERT INTO escreve VALUES(87, 33);
INSERT INTO escreve VALUES(88, 33);
INSERT INTO escreve VALUES(89, 34);
INSERT INTO escreve VALUES(90, 34);
INSERT INTO escreve VALUES(91, 34);
INSERT INTO escreve VALUES(92, 35);
INSERT INTO escreve VALUES(93, 36);
INSERT INTO escreve VALUES(94, 36);
INSERT INTO escreve VALUES(95, 36);
INSERT INTO escreve VALUES(96, 37);
INSERT INTO escreve VALUES(97, 37);
INSERT INTO escreve VALUES(98, 37);
INSERT INTO escreve VALUES(99, 38);
INSERT INTO escreve VALUES(100, 38);
INSERT INTO escreve VALUES(101, 39);
INSERT INTO escreve VALUES(102, 39);
INSERT INTO escreve VALUES(103, 40);
INSERT INTO escreve VALUES(104, 40);
INSERT INTO escreve VALUES(83, 41);
INSERT INTO escreve VALUES(105, 41);
INSERT INTO escreve VALUES(106, 41);
INSERT INTO escreve VALUES(26, 42);
INSERT INTO escreve VALUES(28, 42);
INSERT INTO escreve VALUES(86, 42);
INSERT INTO escreve VALUES(107, 43);
INSERT INTO escreve VALUES(108, 43);
INSERT INTO escreve VALUES(109, 44);
INSERT INTO escreve VALUES(110, 44);
INSERT INTO escreve VALUES(111, 45);
INSERT INTO escreve VALUES(25, 45);
/
/	
/**
 * Inserção na tabela organiza
 * @idOrg						identificador do organizador (pessoa) 
 * @codEv 						código do evento
 * @numEd						número da edição do evento
 * @cargoOrg					cargo do organizador
 */
/
-- Edição 2011
INSERT INTO organiza VALUES(1, 1, 29, 'Coordenador Geral');
INSERT INTO organiza VALUES(2, 1, 29, 'Coordenadores do Comitê de Programa');
INSERT INTO organiza VALUES(3, 1, 29, 'Coordenadores do Comitê de Programa');
INSERT INTO organiza VALUES(4, 1, 29, 'Coordenador de Palestras e Tutoriais');
INSERT INTO organiza VALUES(5, 1, 29, 'Coordenador de Painéis e Debates');
INSERT INTO organiza VALUES(6, 1, 29, 'Coordenadora de Minicursos');
INSERT INTO organiza VALUES(7, 1, 29, 'Coordenador de Workshops');
INSERT INTO organiza VALUES(8, 1, 29, 'Coordenador do Salão de Ferramentas');
/
-- Edição 2012
INSERT INTO organiza VALUES(40, 1, 30, 'Coordenador Geral');
INSERT INTO organiza VALUES(41, 1, 30, 'Coordenador Geral');
INSERT INTO organiza VALUES(42, 1, 30, 'Coordenadores do Comitê de Programa');
INSERT INTO organiza VALUES(43, 1, 30, 'Coordenador de Palestras e Tutoriais');
INSERT INTO organiza VALUES(44, 1, 30, 'Coordenadora de Minicursos');
INSERT INTO organiza VALUES(45, 1, 30, 'Coordenador de Workshops');
INSERT INTO organiza VALUES(46, 1, 30, 'Coordenador do Salão de Ferramentas');
INSERT INTO organiza VALUES(47, 1, 30, 'Coordenador do Salão de Ferramentas');
/
-- Edição de 2013
INSERT INTO organiza VALUES(74, 1, 31, 'Coordenação Geral');
INSERT INTO organiza VALUES(75, 1, 31, 'Coordenação Geral');
INSERT INTO organiza VALUES(76, 1, 31, 'Coordenação Geral');
INSERT INTO organiza VALUES(77, 1, 31, 'Coordenação do Comitê de Programa');
INSERT INTO organiza VALUES(78, 1, 31, 'Coordenação de Painéis e Debates');
INSERT INTO organiza VALUES(79, 1, 31, 'Coordenação de Minicursos');
INSERT INTO organiza VALUES(80, 1, 31, 'Coordenação de Palestras e Tutoriais');
INSERT INTO organiza VALUES(81, 1, 31, 'Coordenação do Salão de Ferramentas');
/
/
/**
 * Inserção na tabela patrocinador
 * @cnpjPat						cnpj do patrocinador
 * @razaoSocialPat				razão social (nome) do patrocinador
 * @telefonePat					telefone do patrocinador
 * @enderecoPat					endereço do patrocinador
 */
/
-- Edição de 2011
INSERT INTO patrocinador VALUES(33654831000136, 'CNPq', '(11) 3833-1298', 'Rua Lago Sul, 206');
INSERT INTO patrocinador VALUES(02012862000160, 'TAM', '(11) 3812-4390', 'Av Jurandir, 856');
INSERT INTO patrocinador VALUES(45691281000143, 'CGI.br', '(35) 5509-3511', 'Av. das Nações Unidas, 11541');
INSERT INTO patrocinador VALUES(23450130000195, 'Polycom', '(41) 3957-1061', 'Av. das Nações Unidas, 12901');
INSERT INTO patrocinador VALUES(00889834000108, 'Capes', '(12) 3826-2045', 'Setor Bancário Norte, 86');
/
-- Edição de 2012
INSERT INTO patrocinador VALUES(42090219094899, 'Google', '(11) 3309-0399', 'Av Paulista, 1049');
INSERT INTO patrocinador VALUES(09489127948391, 'Hp', '(11) 3873-0083', 'Marginal Pinheiros, 984');
INSERT INTO patrocinador VALUES(87348923387583, 'Petrobras', '(21) 3983-8790', 'Av Castelo Branco, 8731');
INSERT INTO patrocinador VALUES(87498927938926, 'Net', '(11) 3321-3864', 'Av Ribeirinha, 2839');
/
-- Edição de 2013
INSERT INTO patrocinador VALUES(13039391100031, 'Terracap', '(11) 3229-3910', 'Rua das Sabiás, 190');
INSERT INTO patrocinador VALUES(90018929301920, 'Embraer', '(11) 3381-3893', 'Rua do Aviador, 233');
INSERT INTO patrocinador VALUES(09398398173799, 'Facitec', '(11) 3828-4929', 'Rua do Comércio, 920');
/
/
/**
 * Inserção na tabela patrocinio
 * @cnpjPat						cnpj do patrocinador
 * @codEv 						código do evento
 * @numEd						número da edição do evento
 * @valorPat					valor do patrocínio
 * @saldoPat					saldo do patrocínio (atributo derivado)
 */
/
-- Edição de 2011
INSERT INTO patrocinio VALUES(33654831000136, 1, 29, 10530.25, 10530.25, to_date('20/05/2011', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(02012862000160, 1, 29, 5560.50, 5560.50, to_date('22/05/2011', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(45691281000143, 1, 29, 2500.00, 2500.00, to_date('21/05/2011', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(23450130000195, 1, 29, 4746.75, 4746.75, to_date('24/05/2011', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(00889834000108, 1, 29, 8420.45, 8420.45, to_date('20/05/2011', 'dd/mm/yyyy'));
/
-- Edição de 2012
INSERT INTO patrocinio VALUES(42090219094899, 1, 30, 8540.50, 8540.50, to_date('25/04/2012', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(09489127948391, 1, 30, 2320.75, 2320.75, to_date('28/04/2012', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(87348923387583, 1, 30, 10120.15, 10120.15, to_date('24/04/2012', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(87498927938926, 1, 30, 1450.25, 1450.25, to_date('27/04/2012', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(45691281000143, 1, 30, 1250.80, 1250.80, to_date('25/04/2012', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(00889834000108, 1, 30, 3340.65, 3340.65, to_date('29/04/2012', 'dd/mm/yyyy'));
/
-- Edição de 2013
INSERT INTO patrocinio VALUES(13039391100031, 1, 31, 2240.20, 2240.20, to_date('01/05/2013', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(90018929301920, 1, 31, 7550.35, 7550.35, to_date('05/05/2013', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(09398398173799, 1, 31, 3220.65, 3220.65, to_date('02/05/2013', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(45691281000143, 1, 31, 2590.15, 2590.15, to_date('03/05/2013', 'dd/mm/yyyy'));
INSERT INTO patrocinio VALUES(42090219094899, 1, 31, 13040.00, 13040.00, to_date('01/05/2013', 'dd/mm/yyyy'));
/
/
/**
 * Inserção na tabela despesa
 * @codDesp							identificador da despesa 
 * @codEv 							código do evento
 * @numEd							número da edição do evento
 * @cnpjPat							cnpj do patrocinador
 * @codEvPat						código do evento
 * @numEdPat						número da edição do evento
 * @dataDesp						data da despesa
 * @valorDesp						valor da despesa
 * @descricaoDesp					descriçao da despesa
 */
/
-- Edição de 2011
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 33654831000136, 1, 29, to_date('03/05/2011', 'dd/mm/yyyy'), 5250.00, 'Despesa com os palestrantes');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 33654831000136, 1, 29, to_date('30/05/2011', 'dd/mm/yyyy'), 1250.54, 'Despesa com decoração do local');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 00889834000108, 1, 29, to_date('03/05/2011', 'dd/mm/yyyy'), 3560.50, 'Despesa com organizadores e técnicos');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 00889834000108, 1, 29, to_date('30/05/2011', 'dd/mm/yyyy'), 2456.15, 'Despesa com coffee break');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 02012862000160, 1, 29, to_date('03/05/2011', 'dd/mm/yyyy'), 4320.00, 'Despesa com aluguel de equipamentos');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 45691281000143, 1, 29, to_date('31/05/2011', 'dd/mm/yyyy'), 550.70, 'Despesa com confecção de kits');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 23450130000195, 1, 29, to_date('25/05/2011', 'dd/mm/yyyy'), 1230.30, 'Despesa com divulgação');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 29, 23450130000195, 1, 29, to_date('03/05/2011', 'dd/mm/yyyy'), 2100.50, 'Despesa com limpeza dos locais');
/
-- Edição de 2012
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 42090219094899, 1, 30, to_date('30/04/2012', 'dd/mm/yyyy'), 4720.00, 'Despesa com os palestrantes');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 09489127948391, 1, 30, to_date('30/04/2012', 'dd/mm/yyyy'), 970.50, 'Despesa com decoração do local');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 87348923387583, 1, 30, to_date('01/05/2012', 'dd/mm/yyyy'), 2904.15, 'Despesa com organizadores e técnicos');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 42090219094899, 1, 30, to_date('01/05/2012', 'dd/mm/yyyy'), 2020.89, 'Despesa com coffee break');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 87348923387583, 1, 30, to_date('02/05/2012', 'dd/mm/yyyy'), 3969.00, 'Despesa com aluguel de equipamentos');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 45691281000143, 1, 30, to_date('02/05/2012', 'dd/mm/yyyy'), 495.75, 'Despesa com confecção de kits');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 87498927938926, 1, 30, to_date('03/05/2012', 'dd/mm/yyyy'), 760.80, 'Despesa com divulgação');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 30, 00889834000108, 1, 30, to_date('03/05/2012', 'dd/mm/yyyy'), 2350.20, 'Despesa com limpeza dos locais');
/
-- Edição de 2013
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 42090219094899, 1, 31, to_date('06/05/2013', 'dd/mm/yyyy'), 5230.00, 'Despesa com os palestrantes');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 13039391100031, 1, 31, to_date('06/05/2013', 'dd/mm/yyyy'), 1040.20, 'Despesa com decoração do local');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 45691281000143, 1, 31, to_date('10/05/2013', 'dd/mm/yyyy'), 1200.50, 'Despesa com organizadores e técnicos');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 90018929301920, 1, 31, to_date('09/05/2013', 'dd/mm/yyyy'), 2530.90, 'Despesa com coffee break');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 42090219094899, 1, 31, to_date('06/05/2013', 'dd/mm/yyyy'), 4110.00, 'Despesa com aluguel de equipamentos');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 90018929301920, 1, 31, to_date('08/05/2013', 'dd/mm/yyyy'), 350.90, 'Despesa com confecção de kits');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 13039391100031, 1, 31, to_date('10/05/2013', 'dd/mm/yyyy'), 897.00, 'Despesa com divulgação');
INSERT INTO despesa VALUES(seq_despesa.NEXTVAL, 1, 31, 42090219094899, 1, 31, to_date('07/05/2013', 'dd/mm/yyyy'), 2720.50, 'Despesa com limpeza dos locais');
/
/
/**
 * Inserção na tabela auxilio
 * @codEvApr							código do evento
 * @numEdApr 							número da edição do evento
 * @idApr 								identificador do apresentador (pessoa)
 * @tipoAux								tipo do auxílio
 * @cnpjPat								cnpj do patrocinador
 * @codEvPat							código do evento
 * @numEdPat							número da edição do evento
 * @valorAux							valor do auxílio dado ao apresentador
 * @dataAux								data que foi dado o auxílio
 */
/
-- Edição de 2011
INSERT INTO auxilio VALUES(33654831000136, 1, 29, 1, 29, 9, 250.00, to_date('29/05/2011','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(33654831000136, 1, 29, 1, 29, 11, 500.00, to_date('30/05/2011','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(02012862000160, 1, 29, 1, 29, 15, 120.00, to_date('03/05/2011','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(02012862000160, 1, 29, 1, 29, 17, 100.00, to_date('30/05/2011','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(45691281000143, 1, 29, 1, 29, 20, 300.00, to_date('31/05/2011','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(45691281000143, 1, 29, 1, 29, 24, 500.00, to_date('01/05/2011','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(23450130000195, 1, 29, 1, 29, 29, 50.00, to_date('03/05/2011','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(23450130000195, 1, 29, 1, 29, 33, 80.00, to_date('31/05/2011','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(00889834000108, 1, 29, 1, 29, 35, 180.00, to_date('01/05/2011','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(00889834000108, 1, 29, 1, 29, 38, 340.00, to_date('03/05/2011','dd/mm/yyyy'), 'HOSPEDAGEM');
/
-- Edição de 2012
INSERT INTO auxilio VALUES(42090219094899, 1, 30, 1, 30, 48, 120.00, to_date('30/04/2012','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(42090219094899, 1, 30, 1, 30, 52, 100.00, to_date('30/04/2012','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(09489127948391, 1, 30, 1, 30, 56, 500.00, to_date('01/05/2012','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(87348923387583, 1, 30, 1, 30, 58, 50.00, to_date('01/05/2012','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(87348923387583, 1, 30, 1, 30, 62, 80.00, to_date('02/05/2012','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(87348923387583, 1, 30, 1, 30, 65, 500.00, to_date('02/05/2012','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(87498927938926, 1, 30, 1, 30, 66, 100.00, to_date('03/05/2012','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(45691281000143, 1, 30, 1, 30, 68, 340.00, to_date('01/05/2012','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(00889834000108, 1, 30, 1, 30, 71, 150.00, to_date('03/05/2012','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(00889834000108, 1, 30, 1, 30, 72, 40.00, to_date('30/04/2012','dd/mm/yyyy'), 'ALIMENTAÇÃO');
/
-- Edição de 2013
INSERT INTO auxilio VALUES(13039391100031, 1, 31, 1, 31, 82, 20.00, to_date('06/05/2013','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(90018929301920, 1, 31, 1, 31, 86, 150.00, to_date('10/05/2013','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(90018929301920, 1, 31, 1, 31, 87, 50.00, to_date('07/05/2013','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(09398398173799, 1, 31, 1, 31, 92, 500.00, to_date('06/05/2013','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(09398398173799, 1, 31, 1, 31, 96, 180.00, to_date('10/05/2013','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(45691281000143, 1, 31, 1, 31, 99, 50.00, to_date('08/05/2013','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(42090219094899, 1, 31, 1, 31, 103, 120.00, to_date('09/05/2013','dd/mm/yyyy'), 'TRANSPORTE');
INSERT INTO auxilio VALUES(42090219094899, 1, 31, 1, 31, 105, 40.00, to_date('07/05/2013','dd/mm/yyyy'), 'ALIMENTAÇÃO');
INSERT INTO auxilio VALUES(42090219094899, 1, 31, 1, 31, 107, 520.00, to_date('06/05/2013','dd/mm/yyyy'), 'HOSPEDAGEM');
INSERT INTO auxilio VALUES(42090219094899, 1, 31, 1, 31, 111, 140.00, to_date('10/05/2013','dd/mm/yyyy'), 'ALIMENTAÇÃO');