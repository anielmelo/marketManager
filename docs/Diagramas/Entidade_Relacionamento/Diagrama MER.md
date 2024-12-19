## 1. Diagrama de Estoque

```plantuml-svg
@startchen

entity fornecedor {
	nome
	cnpj
	endereco
}

entity produto {
	id_fornecedor
	nome
	marca
	custo
}

entity lote {
	id_produto
	id_recebimento
	quantidade_disponivel
	quantidade_total
}

entity recebimento {
	data
}

relationship fornece {
}

relationship contem {
}

relationship pertence {
}

fornecedor -1- fornece
fornece -N- produto

produto -1- pertence
pertence -N- lote

recebimento -1- contem
contem -N- lote

@endchen
```

## 2. Diagrama de Venda

```plantuml-svg
@startchen

entity venda {
	data
}

entity venda_produto {
	id_venda
	id_produto
	quantidade
	valor
	total
}

relationship pertence {
}

venda_produto -N- pertence
pertence -1- venda

@endchen
```

## 3. Diagrama de Autenticação

```plantuml-svg
@startchen

entity usuario {
	nome
	senha
	cargo
}

@endchen
```

## 4. Diagrama sequencial

```plantuml-svg
@startuml

actor Usuario as U
entity API_Estoque as SE
entity API_Venda as SV
entity API_Autenticacao as SA
database DB_Autenticacao as DA
database DB_Venda as DV
database DB_Estoque as DE


' Passo 1: Autenticação
U -> SA : Solicita login (nome, senha)
SA -> DA : Verifica credenciais no DB_Autenticacao
DA -> SA : Retorna resultado da autenticação
alt Sucesso
    SA -> U : Confirma login
else Falha
    SA -> U : Informa erro de login
end

' Passo 2: Realização da Venda
U -> SV : Inicia venda (produtos e quantidades)
SV -> DV : Registra venda no DB_Venda
SV -> DV : Registra itens da venda (venda_produto)
SV -> SE : Solicita atualização do estoque (produto e quantidade)
SE -> DE : Verifica quantidade disponível no DB_Estoque
alt Quantidade suficiente
    SE -> DE : Atualiza estoque (diminui quantidade)
    SE -> SV : Confirma atualização do estoque
else Quantidade insuficiente
    SE -> SV : Informa que não há estoque suficiente
end

' Passo 3: Finalização da Venda
SV -> U : Informa venda realizada com sucesso

@enduml
```