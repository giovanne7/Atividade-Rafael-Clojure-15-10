(ns sistema-notas.core
  (:gen-class))

(def limite-aprovacao 60)
(def mensagens
  {:excelente "Turma excelente!"
   :bom        "Bom desempenho!"
     :ruim     "É necessário melhorar!"})

(defn conceito [nota]
  (cond
    (>= nota 90) "A"
    (>= nota 80) "B"
      (>= nota 70) "C"
    (>= nota 60) "D"
    :else        "F"))

(defn -main []
  (println "Quantos alunos na turma?")
   (let [qtd (Integer/parseInt (read-line))]
    (loop [i 1
           soma 0
       aprovados 0]
      (if (<= i qtd)
        (do
          (println (str "\nNome do aluno " i ":"))
           (let [nome (read-line)]
             (println "Nota:")
              (let [nota (Double/parseDouble (read-line))
                    conc (conceito nota)
                    novo-soma (+ soma nota)
                 novo-aprov (+ aprovados (if (>= nota limite-aprovacao) 1 0))]
                (println (str nome " - Conceito: " conc))
                (recur (inc i) novo-soma novo-aprov)))))
        (let [media (/ soma qtd)
              desempenho (if (>= media 80)
                           (:excelente mensagens)
                      (if (>= media 60)
                         (:bom mensagens)
                         (:ruim mensagens)))]
        (println "\nMédia da turma:" media)
        (println "Aprovados:" aprovados)
          (println "Desempenho geral:" desempenho)))))


