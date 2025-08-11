# 🐾 Gitagotchi – Your Desktop Pet That Lives on Your Screen

**Gitagotchi** is a fun little **Java desktop pet** that wanders across your screen, jumps occasionally, and reacts to physics like gravity.  
You can even extend it to track Git commits, store stats, and give your pet a real personality!  

---

## ✨ Features
- 🖥 **Always-on-top transparent window** so your pet sits on your desktop  
- 🏃 **Smooth wandering** with randomised direction and idle times  
- 🪂 **Gravity simulation** so your pet falls naturally  
- 🐇 **Random jumps** for a playful feel  
- 🔄 **Sprite flipping** so your pet faces its walking direction *(optional)*  
- 📦 **JSON-based pet stats** for persistence *(planned feature)*

---

## 📸 Demo  
<img width="932" height="898" alt="image" src="https://github.com/user-attachments/assets/8d579438-6299-468f-8876-91272ba730b8" />



---

## 🛠 Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/Gitagotchi.git
cd Gitagotchi
```

### 2️⃣ Compile the Code
```bash
javac Main.java PetWindow.java Movement.java
```

### 3️⃣ Run the Pet
```bash
java Main
```

---

## 📂 Project Structure
```
Gitagotchi/
 ├── Main.java          # Entry point – starts the pet
 ├── PetWindow.java     # Handles the pet window and sprite display
 ├── Movement.java      # Controls movement, gravity, and jumps
 ├── resources/         # Your pet images (PNG sprites)
 └── README.md
```

---

## ⚙️ How It Works
- **`PetWindow`** creates a transparent window and displays your pet sprite.  
- **`Movement`** runs in a separate thread, updating position with smooth motion, gravity, and random jumps.  
- You can easily swap the sprite in `resources/` for a custom pet.  
- *(Optional)* Pet stats can be saved to and loaded from a JSON file.

---

## 🐾 Roadmap
- [ ] Save & load pet stats with JSON (hunger, happiness, etc.)  
- [ ] Animate walking & idle frames  
- [ ] Integrate with Git commits (pet gets happy when you commit!)  
- [ ] Add multiple pets on screen  
- [ ] Allow interactions (feeding, playing, sleeping)

---

## 📜 License
MIT License – feel free to use and modify as you like.

---

## 💡 Credits
- Built with **Java Swing** for UI and rendering  
- Inspired by classic desktop pets & Tamagotchi  
