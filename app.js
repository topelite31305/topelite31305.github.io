// ---------- Quiz data ----------
const quiz = [
  {
    q: "Applications that record your location using GPS are common. Which of the following is a legitimate concern when using these apps?",
    options: [
      "They drain battery a little",
      "Location history can reveal your home, workplace, and daily routine",
      "They make the screen brighter",
      "They use a lot of storage space"
    ],
    answer: 1,
    why: "Battery and storage are minor inconveniences. A location history can expose where you live, work, and travel, which is why it is treated as sensitive data under laws such as the GDPR and the CPRA."
  },
  {
    q: "What data most likely could be captured when using a navigation system?",
    options: [
      "Your fingerprint",
      "Your bank PIN",
      "Destinations, routes, and timestamps of your trips",
      "Your browser's saved passwords"
    ],
    answer: 2,
    why: "Navigation apps naturally record where you asked to go, the path you took, and when. A bank PIN and saved passwords are not something a map app would normally access."
  },
  {
    q: "What is the purpose of non-disclosure agreements (NDAs)?",
    options: [
      "To make a company's website load faster",
      "To give employees a raise",
      "To legally require that confidential information be kept private",
      "To register a trademark"
    ],
    answer: 2,
    why: "An NDA is a contract. It creates a legal obligation to keep specified information confidential. It is an example of how law supports an ethical duty of confidentiality."
  },
  {
    q: "Which of the following options can assist you in protecting your privacy?",
    options: [
      "Reusing one password for every account",
      "Posting your home address publicly",
      "Sharing your login details with friends",
      "Using a password manager with two-factor authentication"
    ],
    answer: 3,
    why: "A password manager lets you use unique strong passwords, and two-factor authentication blocks most attacks that succeed with a stolen password alone."
  },
  {
    q: "Which of the following is NOT a concern when trying to protect your Internet privacy?",
    options: [
      "Third-party trackers embedded in websites",
      "Data brokers selling profiles built from your activity",
      "Interception of data on unsecured public Wi-Fi",
      "Using strong, unique passwords for each account"
    ],
    answer: 3,
    why: "Strong, unique passwords are a protection, not a threat. The other three are real privacy concerns that people should understand."
  }
];

function renderQuiz() {
  const box = document.getElementById("quiz");
  let answered = 0;
  let correct = 0;

  const scoreEl = document.getElementById("score");
  function updateScore() {
    scoreEl.textContent = "Score: " + correct + " of " + quiz.length + " correct" +
      (answered === quiz.length ? " (complete)" : "");
  }

  quiz.forEach((item, i) => {
    const card = document.createElement("div");
    card.className = "card";

    const q = document.createElement("p");
    q.className = "question";
    q.textContent = (i + 1) + ". " + item.q;
    card.appendChild(q);

    const feedback = document.createElement("p");
    feedback.className = "feedback";

    const buttons = item.options.map((opt, j) => {
      const btn = document.createElement("button");
      btn.textContent = opt;
      btn.className = "option";
      btn.addEventListener("click", () => {
        if (card.dataset.done) return;
        card.dataset.done = "true";
        answered++;
        const isCorrect = j === item.answer;
        if (isCorrect) correct++;

        buttons.forEach(b => b.disabled = true);
        btn.classList.add(isCorrect ? "right" : "wrong");
        buttons[item.answer].classList.add("right");

        feedback.className = "feedback " + (isCorrect ? "right" : "wrong");
        feedback.textContent = (isCorrect ? "Correct. " : "Not quite. ") + item.why;
        updateScore();
      });
      card.appendChild(btn);
      return btn;
    });

    card.appendChild(feedback);
    box.appendChild(card);
  });

  updateScore();
}

// ---------- Ethics vs. law sorter ----------
const sorterItems = [
  {
    text: "A company collects location data without telling users, even though no statute specifically forbids it in their state.",
    answer: "ethical",
    why: "This is primarily an ethical problem: the lack of transparency is wrong even where the law does not yet prohibit it. Some jurisdictions may now have rules that cover it, so check the law where you are."
  },
  {
    text: "A company in the EU processes location data without a lawful basis such as consent.",
    answer: "legal",
    why: "The GDPR requires a lawful basis for processing personal data, so this would violate a specific regulation. Ethics also applies, but the central issue here is a legal requirement."
  },
  {
    text: "A developer decides to store passwords in plain text because it is faster to build, even though no law in their country directly addresses it.",
    answer: "both",
    why: "Plain-text passwords are an ethical failure because they put users at risk. Depending on the jurisdiction and the data involved, they may also breach data-protection or security obligations."
  },
  {
    text: "A software vendor sells personal profiles to data brokers, and its privacy policy says it does not sell data.",
    answer: "both",
    why: "The deception is ethically problematic, and it can also be unfair or deceptive under laws such as Section 5 of the FTC Act in the United States."
  }
];

function renderSorter() {
  const box = document.getElementById("sorter");
  sorterItems.forEach((item, i) => {
    const row = document.createElement("div");
    row.className = "card";
    const p = document.createElement("p");
    p.textContent = (i + 1) + ". " + item.text;
    row.appendChild(p);

    const feedback = document.createElement("p");
    feedback.className = "feedback";

    ["ethical", "legal", "both"].forEach(choice => {
      const btn = document.createElement("button");
      btn.textContent = choice.charAt(0).toUpperCase() + choice.slice(1);
      btn.addEventListener("click", () => {
        const ok = choice === item.answer;
        feedback.className = "feedback " + (ok ? "right" : "wrong");
        feedback.textContent = (ok ? "Matches. " : "Consider again. The best answer is: " + item.answer + ". ") + item.why;
      });
      row.appendChild(btn);
    });

    row.appendChild(feedback);
    box.appendChild(row);
  });
}

// ---------- Privacy Lab (interactive code) ----------
const permissions = [
  { id: "loc-always", label: "Location: Always (including in background)", risk: 3,
    note: "Background access can build a detailed movement history. Choose 'While using the app' unless a feature truly needs more." },
  { id: "loc-using", label: "Location: While using the app", risk: 1,
    note: "Limited to when you are actively using the app. Lower exposure." },
  { id: "contacts", label: "Contacts", risk: 2,
    note: "Shares other people's information, who never agreed to the app. Consider whether the feature needs it." },
  { id: "microphone", label: "Microphone", risk: 2,
    note: "Can capture audio in unexpected moments. Grant only if you use voice features." },
  { id: "camera", label: "Camera", risk: 1,
    note: "Usually low risk when limited to the app's features, but still review why it is needed." },
  { id: "health", label: "Health and activity data", risk: 2,
    note: "Health data is especially sensitive and is protected more strictly under many laws." },
  { id: "tracking", label: "Allow cross-app tracking for ads", risk: 3,
    note: "Lets data follow you across other companies' apps and websites. Often the most privacy-costly choice." }
];

function renderPermissions() {
  const box = document.getElementById("permissions");
  permissions.forEach(p => {
    const label = document.createElement("label");
    label.className = "perm";
    const cb = document.createElement("input");
    cb.type = "checkbox";
    cb.id = p.id;
    label.appendChild(cb);
    label.appendChild(document.createTextNode(" " + p.label));
    box.appendChild(label);
  });
}

function checkRisk() {
  const result = document.getElementById("riskResult");
  const chosen = permissions.filter(p => document.getElementById(p.id).checked);

  if (chosen.length === 0) {
    result.textContent = "You granted no permissions. The app may not work well, but your exposure is minimal.";
    return;
  }

  const total = chosen.reduce((sum, p) => sum + p.risk, 0);
  const max = permissions.reduce((sum, p) => sum + p.risk, 0);
  const level = total / max < 0.35 ? "Low" : total / max < 0.65 ? "Moderate" : "High";

  result.innerHTML = "";
  const heading = document.createElement("strong");
  heading.textContent = "Overall exposure: " + level + " (" + total + " of " + max + " risk points)";
  result.appendChild(heading);

  const list = document.createElement("ul");
  chosen.forEach(p => {
    const li = document.createElement("li");
    li.textContent = p.label + ": " + p.note;
    list.appendChild(li);
  });
  result.appendChild(list);
}

// ---------- Startup ----------
document.addEventListener("DOMContentLoaded", () => {
  renderSorter();
  renderQuiz();
  renderPermissions();
  document.getElementById("checkRisk").addEventListener("click", checkRisk);
});
