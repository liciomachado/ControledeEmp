SELECT * FROM controledeempenhos.empenho as c inner join empresa as b on c.idEmpresa = b.idempresa  
where not exists (
					select a.idEmpenho,sum(a.valorTotal),c.valorTotal from notafiscal as A
					inner join empenho as b on a.idempenho = b.idempenho
					GROUP BY a.idEmpenho HAVING sum(a.valorTotal) = c.valorTotal and a.idEmpenho = c.idempenho
				);